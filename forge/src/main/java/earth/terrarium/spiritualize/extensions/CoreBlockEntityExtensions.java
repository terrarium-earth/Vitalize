package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.api.AbstractEnergy;
import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlockEntity;
import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import earth.terrarium.spiritualize.util.extensions.Ignored;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExtensionFor(SpiritualizationCoreBlockEntity.class)
public class CoreBlockEntityExtensions extends BlockEntity {
    private LazyOptional<IEnergyStorage> energyOptional;

    public CoreBlockEntityExtensions(BlockEntityType<?> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @ExtensionImplementation
    public void setEnergy(AbstractEnergy arg) {
        this.energyOptional = LazyOptional.of(() -> (IEnergyStorage) arg);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap.equals(CapabilityEnergy.ENERGY) ? energyOptional.cast() : super.getCapability(cap, side);
    }
}
