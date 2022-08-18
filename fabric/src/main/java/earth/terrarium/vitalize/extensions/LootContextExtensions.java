package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.api.ModifiedLootContext;
import earth.terrarium.vitalize.api.ModifiedLootingCondition;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;

@ExtensionFor(ModifiedLootContext.class)
public class LootContextExtensions {
    private int lootingAmount;

    @ExtensionImplementation
    public LootItemCondition replaceConditions(LootItemCondition lootItemCondition) {
        if(lootItemCondition instanceof LootItemRandomChanceWithLootingCondition condition) {
            return new ModifiedLootingCondition(lootingAmount, condition);
        }
        return null;
    }
}
