package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.api.SpiritualizeLootContext;
import earth.terrarium.spiritualize.api.SpiritualizeLootingCondition;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;

@ExtensionFor(SpiritualizeLootContext.class)
public class LootContextExtensions {
    private int lootingAmount;

    @ExtensionImplementation
    public int getLootingModifier() {
        return lootingAmount;
    }

}
