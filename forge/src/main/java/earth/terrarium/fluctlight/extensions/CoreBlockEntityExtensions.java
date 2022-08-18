package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.api.AbstractEnergy;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.util.extensions.ExtensionDeclaration;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.registry.SpiritItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExtensionFor(SoulTranslatorBlockEntity.class)
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

    @ExtensionImplementation
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        return blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).isPresent();
    }

    @ExtensionImplementation
    public static boolean hasSpace(BlockEntity container, Direction direction) {
        return container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction)
                .map(iItemHandler -> ItemHandlerHelper.insertItemStacked(iItemHandler, SpiritItems.SOUL_CRYSTAL_SHARD.get().getDefaultInstance(), true).isEmpty())
                .orElse(false);
    }

    @ExtensionImplementation
    public static void handleItemInsertion(Level level, BlockEntity container, Direction direction, ObjectArrayList<ItemStack> items) {
        container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).ifPresent(iItemHandler -> {
            for (ItemStack item : items) {
                ItemHandlerHelper.insertItemStacked(iItemHandler, item, false);
            }
        });
    }
}
