package earth.terrarium.vitalize;

import earth.terrarium.vitalize.registry.VitalizeBlocks;
import net.fabricmc.api.ModInitializer;
import team.reborn.energy.api.EnergyStorage;

public class VitalizeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Vitalize.init();
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> (EnergyStorage) blockEntity, VitalizeBlocks.SOUL_TRANSLATOR_ENTITY.get());
    }
}