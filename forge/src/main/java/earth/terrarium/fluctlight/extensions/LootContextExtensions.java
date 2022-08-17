package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.api.FluctlightLootContext;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;

@ExtensionFor(FluctlightLootContext.class)
public class LootContextExtensions {
    private int lootingAmount;

    @ExtensionImplementation
    public int getLootingModifier() {
        return lootingAmount;
    }

}
