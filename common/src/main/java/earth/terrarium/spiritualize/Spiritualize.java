package earth.terrarium.spiritualize;

import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import earth.terrarium.spiritualize.registry.SpiritualizeItems;

public class Spiritualize {
    public static final String MOD_ID = "spiritualize";

    public static void init() {
        SpiritualizeBlocks.register();
        SpiritualizeItems.register();
    }
}