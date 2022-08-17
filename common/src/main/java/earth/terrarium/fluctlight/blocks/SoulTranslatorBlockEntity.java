package earth.terrarium.fluctlight.blocks;

import earth.terrarium.fluctlight.api.AbstractEnergy;
import earth.terrarium.fluctlight.api.PylonType;
import earth.terrarium.fluctlight.api.FluctlightLootContext;
import earth.terrarium.fluctlight.registry.ExtraDataMenuProvider;
import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.util.extensions.ExtensionDeclaration;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SoulTranslatorBlockEntity extends BlockEntity implements AbstractEnergy, WorldlyContainer, IAnimatable, ContainerData, ExtraDataMenuProvider {
    private EntityType<?> entityType;
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private List<BasePylonBlock> pylons;
    private int maxTickTime;
    private int tickTime;
    private int maxEnergy;
    private long energyLevel;
    public static final int TICK_TIME = 0;
    public static final int MAX_TICK_TIME = 1;
    public static final int ENERGY = 2;
    public static final int MAX_ENERGY = 3;

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


    public SoulTranslatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get(), blockPos, blockState);
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

    public FluctlightLootContext modifyContext(FluctlightLootContext context) {
        checkAndRun(pylons, pylon -> pylon.modifyLootContext(context));
        return context;
    }

    public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> itemStacks) {
        checkAndRun(pylons, pylon -> pylon.modifyLootTable(itemStacks, (ServerLevel) this.getLevel(), this.getEntityType()));
        return itemStacks;
    }

    public void tick() {
        if (maxTickTime <= 0 && !getCrystal().isEmpty() && validatePylons()) {
            maxTickTime = getDefaultTickTime(); //TODO get from crystal
            maxEnergy = getDefaultEnergyCost(); //TODO get from pylons
            checkAndRun(pylons, pylon -> pylon.onStart(this));
        } else if (maxTickTime > 0) {
            if (tickTime < maxTickTime) {
                tickTime++;
            } else {
                tickTime = 0;
                maxTickTime = 0;
                checkAndRun(pylons, pylon -> pylon.onEnd(this));
            }
        }
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
        if(this.getLevel() != null) {
            for (BlockPos pylonPosition : PYLON_POSITIONS) {
                BlockPos offset = this.getBlockPos().offset(pylonPosition);
                if(!(this.getLevel().getBlockState(offset).getBlock() instanceof BasePylonBlock)) return false;
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
        return entityType;
    }

    public ItemStack getCrystal() {
        return getItem(0);
    }

    public int getDefaultTickTime() {
        if(this.getLevel() != null) {
            Tier tier = SoulUtils.getTier(getCrystal(), this.getLevel());
            if(tier != null) {
                return getLevel().getRandom().nextInt(tier.minSpawnDelay(), tier.maxSpawnDelay());
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
    public void setItem(int i, ItemStack itemStack) {
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
        animationData.addAnimationController(new AnimationController<>(this, "spin_cycle", 5, event -> {
            if (this.tickTime > 0) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.soul_translator.spin", true));
                return PlayState.CONTINUE;
            }
            event.getController().markNeedsReload();
            return PlayState.STOP;
        }));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int get(int i) {
        return switch (i) {
            case TICK_TIME -> this.tickTime;
            case MAX_TICK_TIME -> this.maxTickTime;
            case ENERGY -> (int) this.energyLevel;
            case MAX_ENERGY -> (int) this.getMaxCapacity();
            default -> 0;
        };
    }

    @Override
    public void set(int i, int j) {
        switch (i) {
            case TICK_TIME -> this.tickTime = j;
            case MAX_TICK_TIME -> this.maxTickTime = j;
            case ENERGY -> this.energyLevel = j;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void writeExtraData(ServerPlayer player, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.getBlockPos());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.fluctlight.soul_translator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SoulTranslatorMenu(this, this, i, inventory, this.getBlockPos());
    }
}
