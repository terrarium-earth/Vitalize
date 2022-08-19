package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.api.AbstractEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = AbstractEnergy.class, remap = false)
public interface EnergyExtensions extends IEnergyStorage {
    @Override
    default int receiveEnergy(int maxAmount, boolean bl) {
        return (int) ((AbstractEnergy) this).insertEnergy(maxAmount);
    }

    @Override
    default int extractEnergy(int maxAmount, boolean bl) {
        return (int) ((AbstractEnergy) this).extractEnergy(maxAmount);
    }

    @Override
    default int getEnergyStored() {
        return (int) ((AbstractEnergy) this).getEnergyLevel();
    }

    @Override
    default int getMaxEnergyStored() {
        return (int) ((AbstractEnergy) this).getMaxCapacity();
    }

    @Override
    default boolean canExtract() {
        return false;
    }

    @Override
    default boolean canReceive() {
        return true;
    }
}
