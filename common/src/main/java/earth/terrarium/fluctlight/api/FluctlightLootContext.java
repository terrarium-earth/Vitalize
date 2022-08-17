package earth.terrarium.fluctlight.api;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.NoSuchElementException;

public class FluctlightLootContext extends LootContext {
    private int lootingAmount;
    private final LivingEntity entity;
    private final Map<LootContextParam<?>, Object> parameters;

    protected FluctlightLootContext(ServerLevel serverLevel, BlockPos blockPos, LivingEntity entity) {
        super(serverLevel.getRandom(), 0, serverLevel, serverLevel.getServer().getLootTables()::get, serverLevel.getServer().getPredicateManager()::get, Map.of(), Map.of());
        this.entity = entity;
        this.parameters = Map.of(LootContextParams.DAMAGE_SOURCE, DamageSource.OUT_OF_WORLD, LootContextParams.ORIGIN, blockPos, LootContextParams.THIS_ENTITY, entity);
    }

    public static FluctlightLootContext of(ServerLevel serverLevel, SoulTranslatorBlockEntity spirtulizationCore) {
        Entity entity = spirtulizationCore.getEntityType().create(serverLevel);
        if (entity instanceof LivingEntity) {
            return new FluctlightLootContext(serverLevel, spirtulizationCore.getBlockPos(), (LivingEntity) entity);
        }
        return null;
    }

    @Override
    public <T> T getParam(@NotNull LootContextParam<T> lootContextParam) {
        var object = this.parameters.get(lootContextParam);
        if (object == null) {
            throw new NoSuchElementException(lootContextParam.getName().toString());
        } else {
            //noinspection unchecked
            return (T) object;
        }
    }

    @Nullable
    @Override
    public <T> T getParamOrNull(@NotNull LootContextParam<T> lootContextParam) {
        //noinspection unchecked
        return (T) this.parameters.get(lootContextParam);
    }

    @Override
    public boolean hasParam(@NotNull LootContextParam<?> lootContextParam) {
        if (lootContextParam == LootContextParams.LAST_DAMAGE_PLAYER) return true;
        return super.hasParam(lootContextParam);
    }

    @Nullable
    @Override
    public LootItemCondition getCondition(@NotNull ResourceLocation resourceLocation) {
        LootItemCondition condition = super.getCondition(resourceLocation);
        LootItemCondition modifiedCondition = replaceConditions(condition);
        return modifiedCondition == null ? condition : modifiedCondition;
    }

    public LootItemCondition replaceConditions(LootItemCondition lootItemCondition) {
        return null;
    }

    public int getLootingAmount() {
        return lootingAmount;
    }

    public void incrementLootingAmount() {
        lootingAmount++;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}
