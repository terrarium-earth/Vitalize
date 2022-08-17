package earth.terrarium.spiritualize.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;

public class SpiritualizeLootingCondition implements LootItemCondition {
    private final LootItemRandomChanceWithLootingCondition condition;
    private final int lootingAmount;

    public SpiritualizeLootingCondition(int lootingAmount, LootItemRandomChanceWithLootingCondition condition) {
        this.condition = condition;
        this.lootingAmount = lootingAmount;
    }

    @Override
    public LootItemConditionType getType() {
        return LootItemConditions.RANDOM_CHANCE_WITH_LOOTING;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.getRandom().nextFloat() < condition.percent + (float) lootingAmount * condition.lootingMultiplier;
    }
}
