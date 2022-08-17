package earth.terrarium.fluctlight.api;

public interface AbstractEnergy {
    long insertEnergy(long maxAmount);
    long extractEnergy(long maxAmount);
    long getEnergyLevel();
    long getMaxCapacity();
}
