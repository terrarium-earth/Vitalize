package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlockEntity;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import team.reborn.energy.api.base.SimpleEnergyStorage;

@SuppressWarnings("UnstableApiUsage")
@ExtensionFor(SpiritualizationCoreBlockEntity.class)
public class SpiritulizationCoreExtensions {

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1000000, 1000000, 0);

    @ExtensionImplementation
    public boolean hasEnoughForOperation(int maxEnergy) {
        return this.energyStorage.getCapacity() > maxEnergy;
    }

    @ExtensionImplementation
    public void drainEnergy(int energyCost) {
        this.energyStorage.extract(energyCost, null);
    }

    @ExtensionImplementation
    public Object getEnergyStorage() {
        return this.energyStorage;
    }
}
