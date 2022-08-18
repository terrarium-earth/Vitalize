package earth.terrarium.vitalize.blocks;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.api.AbstractEnergy;
import earth.terrarium.vitalize.api.LootTableUtils;
import earth.terrarium.vitalize.api.PylonType;
import earth.terrarium.vitalize.api.ModifiedLootContext;
import earth.terrarium.vitalize.registry.ExtraDataMenuProvider;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.util.extensions.ExtensionDeclaration;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.data.Tier;
import me.codexadrian.spirit.utils.SoulUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SoulRevitalizerBlockEntity extends BlockEntity implements AbstractEnergy, WorldlyContainer, IAnimatable, ContainerData, ExtraDataMenuProvider {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private final List<BasePylonBlock> pylons = new ArrayList<>();
    private int maxTickTime;
    private int tickTime;
    private int maxEnergy;
    private long energyLevel;
    public static final int TICK_TIME = 0;
    public static final int MAX_TICK_TIME = 1;
    public static final int ENERGY = 2;
    public static final int MAX_ENERGY = 3;
    public static final int ENERGY_CONSUMPTION = 4;

    private final AnimationFactory factory = new AnimationFactory(this);

    private static final List<BlockPos> PYLON_POSITIONS = Util.make(Lists.newArrayList(), positions -> {
        positions.add(new BlockPos(-3, 0, 3));
        positions.add(new BlockPos(-3, 0, -3));
        positions.add(new BlockPos(3, 0, -3));
        positions.add(new BlockPos(3, 0, 3));
        positions.add(new BlockPos(4, 0, 0));
        positions.add(new BlockPos(-4, 0, 0));
        positions.add(new BlockPos(0, 0, 4));
        positions.add(new BlockPos(0, 0, -4));
    });


    public SoulRevitalizerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VitalizeBlocks.SOUL_TRANSLATOR_ENTITY.get(), blockPos, blockState);
        this.setEnergy(this);
    }

    @ExtensionDeclaration
    public void setEnergy(AbstractEnergy energy) {
    }

    @Override
    public void load(CompoundTag compoundTag) {
        this.energyLevel = compoundTag.getLong("Energy");
        this.tickTime = compoundTag.getInt("BurnTime");
        this.maxTickTime = compoundTag.getInt("MaxBurnTime");
        ContainerHelper.loadAllItems(compoundTag, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putLong("Energy", this.energyLevel);
        compoundTag.putInt("BurnTime", tickTime);
        compoundTag.putInt("MaxBurnTime", maxTickTime);
        ContainerHelper.saveAllItems(compoundTag, inventory);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag);
        return compoundTag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ModifiedLootContext modifyContext(ModifiedLootContext context) {
        checkAndRun(pylons, pylon -> pylon.modifyLootContext(context));
        return context;
    }

    public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> itemStacks) {
        checkAndRun(pylons, pylon -> pylon.modifyLootTable(itemStacks, (ServerLevel) this.getLevel(), this.getEntityType()));
        return itemStacks;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, SoulRevitalizerBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (blockEntity.maxTickTime <= 0 && blockEntity.validate()) {
                if (blockEntity.getEntityType() == null) return;
                blockEntity.maxTickTime = blockEntity.getDefaultTickTime();
                blockEntity.maxEnergy = blockEntity.getDefaultEnergyCost();
                checkAndRun(blockEntity.pylons, pylon -> pylon.onStart(blockEntity));
                blockEntity.setChanged();
                level.sendBlockUpdated(blockPos, blockState, blockState, Block.UPDATE_ALL);
            } else if (blockEntity.getMaxTickTime() > 0) {
                if (level.getGameTime() % 5 == 0 || blockEntity.getEnergyLevel() < blockEntity.getEnergyCost()) {
                    if (blockEntity.getEntityType() == null || !blockEntity.validate() || blockEntity.getEnergyLevel() < blockEntity.getEnergyCost())  {
                        blockEntity.clear();
                        return;
                    }
                }
                if (blockEntity.getEntityType() != null) {
                    if (blockEntity.tickTime < blockEntity.getMaxTickTime()) {
                        blockEntity.tickTime++;
                        blockEntity.extractEnergy(blockEntity.getEnergyCost());
                    } else {
                        blockEntity.clear();
                        if (blockEntity.validate()) {
                            checkAndRun(blockEntity.pylons, pylon -> pylon.onEnd(blockEntity));
                            Tier tier = SoulUtils.getTier(blockEntity.getCrystal(), level);
                            if(tier != null) {
                                ObjectArrayList<ItemStack> lootTable = LootTableUtils.getLootTable(blockEntity, (ServerLevel) level, tier.spawnCount());
                                Direction containerDir = findNearestContainer(level, blockPos);
                                if (containerDir != null) {
                                    handleItemInsertion(level, level.getBlockEntity(blockPos.relative(containerDir)), containerDir.getOpposite(), lootTable);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean validate() {
        return this.validatePylons() && validateInventories();
    }

    public boolean validateInventories() {
        if(this.getLevel() == null) return false;
        Direction containerDir = findNearestContainer(getLevel(), this.getBlockPos());
        return containerDir != null && hasSpace(getLevel().getBlockEntity(this.getBlockPos().relative(containerDir)), containerDir.getOpposite());
    }

    public void clear() {
        if(this.getLevel() == null) return;
        this.tickTime = 0;
        this.maxTickTime = 0;
        this.setChanged();
        getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
    }

    public static Direction findNearestContainer(Level level, BlockPos blockPos) {
        for (Direction direction : Direction.values()) {
            BlockPos offsetPos = blockPos.relative(direction);
            BlockEntity blockEntity = level.getBlockEntity(offsetPos);
            if (blockEntity != null) {
                if (isContainer(blockEntity, direction.getOpposite()) && hasSpace(blockEntity, direction.getOpposite())) {
                    return direction;
                }
            }
        }
        return null;
    }

    @ExtensionDeclaration
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        throw new AssertionError();
    }

    @ExtensionDeclaration
    @Contract(pure = true)
    public static boolean hasSpace(BlockEntity blockEntity, Direction direction) {
        throw new AssertionError();
    }

    @ExtensionDeclaration
    public static void handleItemInsertion(Level level, BlockEntity containerPos, Direction direction, ObjectArrayList<ItemStack> items) {
        throw new AssertionError();
    }

    public static void checkAndRun(List<BasePylonBlock> pylons, Consumer<PylonType> consumer) {
        Map<ResourceLocation, Integer> amount = new HashMap<>();
        for (BasePylonBlock pylon : pylons) {
            if (amount.merge(pylon.getType().getId(), 1, Integer::sum) <= pylon.getType().maxLevel()) {
                consumer.accept(pylon.getType());
            }
        }
    }

    public boolean validatePylons() {
        this.pylons.clear();
        if(this.getLevel() != null) {
            for (BlockPos pylonPosition : PYLON_POSITIONS) {
                BlockPos offset = this.getBlockPos().offset(pylonPosition);
                Block block = this.getLevel().getBlockState(offset).getBlock();
                if(!(block instanceof BasePylonBlock)) {
                    pylons.clear();
                    return false;
                } else {
                    pylons.add((BasePylonBlock) block);
                }
            }
            return true;
        }
        return false;
    }

    public void setMaxTickTime(int maxTickTime) {
        this.maxTickTime = maxTickTime;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getMaxTickTime() {
        return maxTickTime;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public EntityType<?> getEntityType() {
        if(!getCrystal().isEmpty()) {
            String soulCrystalType = SoulUtils.getSoulCrystalType(this.getCrystal());
            if (soulCrystalType != null) {
                return EntityType.byString(soulCrystalType).orElse(null);
            }
        }
        return null;
    }

    public ItemStack getCrystal() {
        return getItem(0);
    }

    public int getDefaultTickTime() {
        if(this.getLevel() != null) {
            Tier tier = SoulUtils.getTier(getCrystal(), this.getLevel());
            if(tier != null) {
                return tier.minSpawnDelay() + tier.maxSpawnDelay();
            }
        }
        return 0;
    }

    public int getDefaultEnergyCost() {
        if(this.getLevel() != null) {
            Tier tier = SoulUtils.getTier(getCrystal(), this.getLevel());
            if(tier != null) {
                return 2 * tier.spawnRange() * tier.spawnCount() * this.getMaxTickTime();
            }
        }
        return 0;
    }

    @Override
    public long insertEnergy(long maxAmount) {
        long moved = Math.min(Math.max(0, maxAmount), this.getMaxCapacity() - this.energyLevel);
        this.energyLevel += moved;
        return moved;
    }

    @Override
    public long extractEnergy(long maxAmount) {
        long moved = Math.min(Math.max(0, maxAmount), this.energyLevel);
        this.energyLevel -= moved;
        return moved;
    }

    @Override
    public long getEnergyLevel() {
        return this.energyLevel;
    }

    @Override
    public long getMaxCapacity() {
        return 1000000;
    }

    @Override
    public int[] getSlotsForFace(@NotNull Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, @NotNull ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        return ContainerHelper.removeItem(inventory, i, j);
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(inventory, i);
    }

    @Override
    public void setItem(int i, @NotNull ItemStack itemStack) {
        this.inventory.set(i, itemStack);
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()) <= 64;
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "spin_cycle", 0, event -> {
            if (event.getAnimatable().maxTickTime > 0) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.soul_translator.spin", true));
                return PlayState.CONTINUE;
            } else {
                event.getController().setAnimation(new AnimationBuilder());
                return PlayState.CONTINUE;
            }
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public int getEnergyCost() {
        return this.getMaxTickTime() == 0 ? 0 : this.getMaxEnergy() / this.getMaxTickTime();
    }

    @Override
    public int get(int i) {
        return switch (i) {
            case TICK_TIME -> this.tickTime;
            case MAX_TICK_TIME -> this.maxTickTime;
            case ENERGY -> (int) this.energyLevel;
            case MAX_ENERGY -> (int) this.getMaxCapacity();
            case ENERGY_CONSUMPTION -> this.getEnergyCost();
            default -> 0;
        };
    }

    @Override
    public void set(int i, int j) {
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void writeExtraData(ServerPlayer player, FriendlyByteBuf buffer) {
        buffer.writeBoolean(!this.validatePylons());
        buffer.writeBoolean(!this.validateInventories());
    }

    @Override
    public Component getDisplayName() {
        return this.getBlockState().getBlock().getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new SoulRevitalizerMenu(this, this, i, inventory, !this.validatePylons(), !this.validateInventories());
    }
}
