package earth.terrarium.spiritualize.api;

import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlockEntity;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTableUtils {

    public static ObjectArrayList<ItemStack> getLootTable(SpiritualizationCoreBlockEntity block, ServerLevel level) {
        var lootTableId = block.getEntityType().getDefaultLootTable();
        LootTable lootTable = level.getServer().getLootTables().get(lootTableId);
        SpiritualizeLootContext context = SpiritualizeLootContext.of(level, block);
        if(lootTable == null || context == null) {
            return new ObjectArrayList<>();
        }
        return block.modifyLootTable(lootTable.getRandomItems(block.modifyContext(context)));
    }
}
