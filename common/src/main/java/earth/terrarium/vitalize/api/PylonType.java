package earth.terrarium.vitalize.api;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface PylonType {

    ResourceLocation getId();

    default ModifiedLootContext modifyLootContext(ModifiedLootContext context) {
        return context;
    }

    default ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
        return lootTable;
    }

    default void onStart(SoulRevitalizerBlockEntity core) {
        core.setMaxEnergy((int) Math.max(1, core.getMaxEnergy() * energyModifier()));
    }

    default void onEnd(ObjectArrayList<ItemStack> drops, SoulRevitalizerBlockEntity core) {}

    int maxLevel();

    double energyModifier();

    List<Component> description();
}
