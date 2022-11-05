package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.registry.SpiritItems;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.msrandom.extensions.annotations.ClassExtension;
import net.msrandom.extensions.annotations.ImplementsBaseElement;
import net.msrandom.extensions.annotations.NonExtensionElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("UnstableApiUsage")
@ClassExtension(SoulRevitalizerBlockEntity.class)
public class SoulVitalizerExtensions {

    @NonExtensionElement
    public SoulVitalizerExtensions() {
    }

    @ImplementsBaseElement
    public static void handleItemInsertion(Level level, BlockEntity blockEntity, Direction direction, ObjectArrayList<ItemStack> items) {
        var storage = ItemStorage.SIDED.find(level, blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction.getOpposite());
        for(ItemStack stack : items) {
            if(stack.isEmpty()) continue;
            var itemVariant = ItemVariant.of(stack);
            try (Transaction txn = Transaction.openOuter()) {
                if (storage != null) {
                    storage.insert(itemVariant, stack.getCount(), txn);
                    txn.commit();
                }
            }
        }
    }

    @ImplementsBaseElement
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        return ItemStorage.SIDED.find(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction) != null;
    }

    @ImplementsBaseElement
    public static boolean hasSpace(BlockEntity blockEntity, Direction direction) {
        Storage<ItemVariant> storageViews = ItemStorage.SIDED.find(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction);
        if(storageViews != null) {
            try (Transaction txn = Transaction.openOuter()) {
                return storageViews.simulateInsert(ItemVariant.of(SpiritItems.CRUDE_SOUL_CRYSTAL.get()), 1, txn) == 1;
            }
        }
        return false;
    }
}
