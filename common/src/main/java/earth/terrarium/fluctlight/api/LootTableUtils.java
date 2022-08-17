package earth.terrarium.fluctlight.api;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTableUtils {

    public static ObjectArrayList<ItemStack> getLootTable(SoulTranslatorBlockEntity block, ServerLevel level) {
        var lootTableId = block.getEntityType().getDefaultLootTable();
        LootTable lootTable = level.getServer().getLootTables().get(lootTableId);
        FluctlightLootContext context = FluctlightLootContext.of(level, block);
        if(lootTable == null || context == null) {
            return new ObjectArrayList<>();
        }
        return block.modifyLootTable(lootTable.getRandomItems(block.modifyContext(context)));
    }
}
