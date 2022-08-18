package earth.terrarium.vitalize.api;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;

public class ModifiedLootingCondition implements LootItemCondition {
    private final LootItemRandomChanceWithLootingCondition condition;
    private final int lootingAmount;

    public ModifiedLootingCondition(int lootingAmount, LootItemRandomChanceWithLootingCondition condition) {
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
