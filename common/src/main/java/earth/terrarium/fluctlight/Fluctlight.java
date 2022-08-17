package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.registry.FluctlightItems;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import earth.terrarium.fluctlight.registry.FluctlightRecipes;

public class Fluctlight {
    public static final String MODID = "fluctlight";

    public static void init() {
        FluctlightBlocks.register();
        FluctlightItems.register();
        FluctlightMenus.register();
        FluctlightRecipes.register();
    }
}