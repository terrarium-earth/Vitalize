package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.api.FluctlightLootContext;
import earth.terrarium.fluctlight.api.FluctlightLootingCondition;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;

@ExtensionFor(FluctlightLootContext.class)
public class LootContextExtensions {
    private int lootingAmount;

    @ExtensionImplementation
    public LootItemCondition replaceConditions(LootItemCondition lootItemCondition) {
        if(lootItemCondition instanceof LootItemRandomChanceWithLootingCondition condition) {
            return new FluctlightLootingCondition(lootingAmount, condition);
        }
        return null;
    }
}
