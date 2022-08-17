package earth.terrarium.spiritualize.api;

import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

public interface PylonType {

    ResourceLocation getId();

    default SpiritualizeLootContext modifyLootContext(SpiritualizeLootContext context) {
        return context;
    }

    default ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
        return lootTable;
    }

    default void onStart(SpiritualizationCoreBlockEntity core) {}

    default void onEnd(SpiritualizationCoreBlockEntity core) {}

    int maxLevel();

    double energyModifier();
}
