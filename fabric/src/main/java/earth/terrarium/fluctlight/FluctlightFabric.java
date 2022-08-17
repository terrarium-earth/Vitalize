package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import net.fabricmc.api.ModInitializer;
import team.reborn.energy.api.EnergyStorage;

public class FluctlightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Fluctlight.init();
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> (EnergyStorage) blockEntity, FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get());
    }
}