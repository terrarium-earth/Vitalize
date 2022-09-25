package earth.terrarium.vitalize;

import net.fabricmc.api.ModInitializer;

public class VitalizeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Vitalize.init();
    }
}