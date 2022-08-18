package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
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

@SuppressWarnings("UnstableApiUsage")
@ExtensionFor(SoulRevitalizerBlockEntity.class)
public class SoulVitalizerExtensions {
    @ExtensionImplementation
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

    @ExtensionImplementation
    public static boolean isContainer(BlockEntity blockEntity, Direction direction) {
        return ItemStorage.SIDED.find(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction) != null;
    }

    @ExtensionImplementation
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
