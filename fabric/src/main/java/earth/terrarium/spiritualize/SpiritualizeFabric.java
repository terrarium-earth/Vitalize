package earth.terrarium.spiritualize;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import net.fabricmc.api.ModInitializer;
import team.reborn.energy.api.EnergyStorage;

public class SpiritualizeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Spiritualize.init();
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> (EnergyStorage) blockEntity, SpiritualizeBlocks.SPIRITUALIZATION_CORE_ENTITY.get());
    }
}