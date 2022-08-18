package earth.terrarium.vitalize;

import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.registry.VitalizeItems;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import earth.terrarium.vitalize.registry.VitalizeRecipes;

public class Vitalize {
    public static final String MODID = "vitalize";

    public static void init() {
        VitalizeBlocks.register();
        VitalizeItems.register();
        VitalizeMenus.register();
        VitalizeRecipes.register();
    }
}