package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import team.reborn.energy.api.base.SimpleEnergyStorage;

@SuppressWarnings("UnstableApiUsage")
@ExtensionFor(SoulTranslatorBlockEntity.class)
public class SoulTranslatorExtensions {

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
