package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.registry.SpiritItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.msrandom.extensions.annotations.ClassExtension;
import net.msrandom.extensions.annotations.ImplementsBaseElement;
import net.msrandom.extensions.annotations.NonExtensionElement;

@ClassExtension(SoulRevitalizerBlockEntity.class)
public abstract class CoreBlockEntityExtensions {

    @NonExtensionElement
    public CoreBlockEntityExtensions() {
    }

    @ImplementsBaseElement
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        return blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).isPresent();
    }

    @ImplementsBaseElement
    public static boolean hasSpace(BlockEntity container, Direction direction) {
        return container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction)
                .map(iItemHandler -> ItemHandlerHelper.insertItemStacked(iItemHandler, SpiritItems.SOUL_CRYSTAL_SHARD.get().getDefaultInstance(), true).isEmpty())
                .orElse(false);
    }

    @ImplementsBaseElement
    public static void handleItemInsertion(Level level, BlockEntity container, Direction direction, ObjectArrayList<ItemStack> items) {
        container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction).ifPresent(iItemHandler -> {
            for (ItemStack item : items) {
                ItemHandlerHelper.insertItemStacked(iItemHandler, item, false);
            }
        });
    }
}
