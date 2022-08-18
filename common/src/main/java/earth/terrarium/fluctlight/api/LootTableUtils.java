package earth.terrarium.fluctlight.api;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.recipes.SpecialDropsData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTableUtils {

    public static ObjectArrayList<ItemStack> getLootTable(SoulTranslatorBlockEntity block, ServerLevel level, int mobs) {
        var lootTableId = block.getEntityType().getDefaultLootTable();
        LootTable lootTable = level.getServer().getLootTables().get(lootTableId);
        LootTable specialDrops = SpecialDropsData.getLootTable(level, block.getEntityType()).map(id -> level.getServer().getLootTables().get(id)).orElse(null);
        FluctlightLootContext context = FluctlightLootContext.of(level, block);
        ObjectArrayList<ItemStack> items = new ObjectArrayList<>();
        if(context != null) {
            FluctlightLootContext lootContext = block.modifyContext(context);
            if(lootTable != null) {
                for (int i = 0; i < mobs; i++) {
                    items.addAll(block.modifyLootTable(lootTable.getRandomItems(lootContext)));
                }
            }
            if(specialDrops != null) {
                for (int i = 0; i < mobs; i++) {
                    items.addAll(specialDrops.getRandomItems(lootContext));
                }
            }
        }
        return items;
    }
}
