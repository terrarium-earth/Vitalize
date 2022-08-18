package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.api.ModifiedLootContext;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;

@ExtensionFor(ModifiedLootContext.class)
public class LootContextExtensions {
    private int lootingAmount;

    @ExtensionImplementation
    public int getLootingModifier() {
        return lootingAmount;
    }

}
