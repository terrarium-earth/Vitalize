package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.api.ModifiedLootContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.msrandom.extensions.annotations.ClassExtension;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;
import java.util.function.Function;

@ClassExtension(ModifiedLootContext.class)
public class LootContextExtensions extends LootContext {
    private int lootingAmount;

    protected LootContextExtensions(RandomSource arg, float f, ServerLevel arg2, Function<ResourceLocation, LootTable> function, Function<ResourceLocation, LootItemCondition> function2, Map<LootContextParam<?>, Object> map, Map<ResourceLocation, DynamicDrop> map2) {
        super(arg, f, arg2, function, function2, map, map2);
    }

    @Override
    public int getLootingModifier() {
        return lootingAmount;
    }
}
