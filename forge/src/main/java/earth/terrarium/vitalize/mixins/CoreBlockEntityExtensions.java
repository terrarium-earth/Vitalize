package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.api.AbstractEnergy;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SoulRevitalizerBlockEntity.class, remap = false)
public abstract class CoreBlockEntityExtensions extends BlockEntity implements IEnergyStorage {
    private LazyOptional<IEnergyStorage> energyOptional;

    public CoreBlockEntityExtensions(BlockEntityType<?> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void setMahEnergy(BlockPos blockPos, BlockState blockState, CallbackInfo ci) {
        this.energyOptional = LazyOptional.of(() -> this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap.equals(CapabilityEnergy.ENERGY) ? energyOptional.cast() : super.getCapability(cap, side);
    }

    @Overwrite
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        return blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).isPresent();
    }

    @Overwrite
    public static boolean hasSpace(BlockEntity container, Direction direction) {
        return container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction)
                .map(iItemHandler -> ItemHandlerHelper.insertItemStacked(iItemHandler, SpiritItems.SOUL_CRYSTAL_SHARD.get().getDefaultInstance(), true).isEmpty())
                .orElse(false);
    }

    @Overwrite
    public static void handleItemInsertion(Level level, BlockEntity container, Direction direction, ObjectArrayList<ItemStack> items) {
        container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).ifPresent(iItemHandler -> {
            for (ItemStack item : items) {
                ItemHandlerHelper.insertItemStacked(iItemHandler, item, false);
            }
        });
    }
}
