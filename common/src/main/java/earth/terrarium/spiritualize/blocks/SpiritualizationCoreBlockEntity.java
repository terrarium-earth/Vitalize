package earth.terrarium.spiritualize.blocks;

import earth.terrarium.spiritualize.api.AbstractEnergy;
import earth.terrarium.spiritualize.api.PylonType;
import earth.terrarium.spiritualize.api.SpiritualizeLootContext;
import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import earth.terrarium.spiritualize.util.extensions.ExtensionDeclaration;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.data.Tier;
import me.codexadrian.spirit.utils.SoulUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SpiritualizationCoreBlockEntity extends BlockEntity implements AbstractEnergy {
    private EntityType<?> entityType;
    private ItemStack crystal = ItemStack.EMPTY;
    private List<BasePylonBlock> pylons;
    private int maxTickTime;
    private int tickTime;
    private int maxEnergy;
    private long energyLevel;

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


    public SpiritualizationCoreBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SpiritualizeBlocks.SPIRITUALIZATION_CORE_ENTITY.get(), blockPos, blockState);
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
        if(compoundTag.contains("Crystal", Tag.TAG_COMPOUND)) {
            this.crystal = ItemStack.of(compoundTag.getCompound("Crystal"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putLong("Energy", this.energyLevel);
        compoundTag.putInt("BurnTime", tickTime);
        compoundTag.putInt("MaxBurnTime", maxTickTime);
        if(!crystal.isEmpty()) {
            compoundTag.put("Crystal", crystal.save(new CompoundTag()));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag);
        return compoundTag;
    }

    public SpiritualizeLootContext modifyContext(SpiritualizeLootContext context) {
        checkAndRun(pylons, pylon -> pylon.modifyLootContext(context));
        return context;
    }

    public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> itemStacks) {
        checkAndRun(pylons, pylon -> pylon.modifyLootTable(itemStacks, (ServerLevel) this.getLevel(), this.getEntityType()));
        return itemStacks;
    }

    public void tick() {
        if (maxTickTime <= 0 && !crystal.isEmpty() && validatePylons()) {
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
        return crystal;
    }

    public int getDefaultTickTime() {
        if(this.getLevel() != null) {
            Tier tier = SoulUtils.getTier(crystal, this.getLevel());
            if(tier != null) {
                return getLevel().getRandom().nextInt(tier.minSpawnDelay(), tier.maxSpawnDelay());
            }
        }
        return 0;
    }

    public int getDefaultEnergyCost() {
        if(this.getLevel() != null) {
            Tier tier = SoulUtils.getTier(crystal, this.getLevel());
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
}
