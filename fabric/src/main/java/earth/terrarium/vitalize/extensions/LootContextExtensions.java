package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.api.ModifiedLootContext;
import earth.terrarium.vitalize.api.ModifiedLootingCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.msrandom.extensions.annotations.ClassExtension;
import net.msrandom.extensions.annotations.ExtensionFieldShadow;
import net.msrandom.extensions.annotations.ImplementsBaseElement;
import net.msrandom.extensions.annotations.NonExtensionElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@ClassExtension(ModifiedLootContext.class)
public class LootContextExtensions {

    @NonExtensionElement
    public LootContextExtensions() {
    }

    @ExtensionFieldShadow
    private int lootingAmount;

    @ImplementsBaseElement
    public LootItemCondition replaceConditions(LootItemCondition lootItemCondition) {
        if(lootItemCondition instanceof LootItemRandomChanceWithLootingCondition condition) {
            return new ModifiedLootingCondition(lootingAmount, condition);
        }
        return null;
    }
}
