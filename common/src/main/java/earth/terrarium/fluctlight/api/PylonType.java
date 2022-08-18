package earth.terrarium.fluctlight.api;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

public interface PylonType {

    ResourceLocation getId();

    default FluctlightLootContext modifyLootContext(FluctlightLootContext context) {
        return context;
    }

    default ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
        return lootTable;
    }

    default void onStart(SoulTranslatorBlockEntity core) {
        core.setMaxEnergy((int) Math.max(1, core.getMaxEnergy() * energyModifier()));
    }

    default void onEnd(SoulTranslatorBlockEntity core) {}

    int maxLevel();

    double energyModifier();
}
