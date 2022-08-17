package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.api.AbstractEnergy;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

@ExtensionFor(AbstractEnergy.class)
public interface EnergyExtensions extends EnergyStorage {
    @Override
    default long insert(long maxAmount, @SuppressWarnings("UnstableApiUsage") TransactionContext transaction){
        return ((AbstractEnergy) this).insertEnergy((int) maxAmount);
    }

    @Override
    default long extract(long maxAmount, @SuppressWarnings("UnstableApiUsage") TransactionContext transaction){
        return ((AbstractEnergy) this).extractEnergy((int) maxAmount);
    }

    @Override
    default long getAmount(){
        return ((AbstractEnergy) this).getEnergyLevel();
    }

    @Override
    default long getCapacity(){
        return ((AbstractEnergy) this).getMaxCapacity();
    }

    @Override
    default boolean supportsExtraction() {
        return false;
    }
}
