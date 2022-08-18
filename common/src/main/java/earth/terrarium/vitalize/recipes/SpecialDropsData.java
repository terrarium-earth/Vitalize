package earth.terrarium.vitalize.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public record SpecialDropsData(ResourceLocation id, EntityType<?> entity, ResourceLocation lootTable) implements CodecRecipe<Container> {
    public static Codec<SpecialDropsData> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                Registry.ENTITY_TYPE.byNameCodec().fieldOf("entity").forGetter(SpecialDropsData::entity),
                ResourceLocation.CODEC.fieldOf("drop").forGetter(SpecialDropsData::lootTable)
        ).apply(instance, SpecialDropsData::new));
    }

    @Override
    public boolean matches(Container container, Level level) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return VitalizeRecipes.SPECIAL_DROPS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return VitalizeRecipes.SPECIAL_DROPS.get();
    }

    public static Optional<ResourceLocation> getLootTable(ServerLevel level, EntityType<?> entity) {
        return level.getRecipeManager().getAllRecipesFor(VitalizeRecipes.SPECIAL_DROPS.get()).stream().filter(recipe -> recipe.entity() == entity).findFirst().map(SpecialDropsData::lootTable);
    }
}
