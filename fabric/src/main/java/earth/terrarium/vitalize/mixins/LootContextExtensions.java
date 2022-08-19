package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.api.ModifiedLootContext;
import earth.terrarium.vitalize.api.ModifiedLootingCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ModifiedLootContext.class, remap = false)
public class LootContextExtensions {
    private int lootingAmount;

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public LootItemCondition replaceConditions(LootItemCondition lootItemCondition) {
        if(lootItemCondition instanceof LootItemRandomChanceWithLootingCondition condition) {
            return new ModifiedLootingCondition(lootingAmount, condition);
        }
        return null;
    }
}
