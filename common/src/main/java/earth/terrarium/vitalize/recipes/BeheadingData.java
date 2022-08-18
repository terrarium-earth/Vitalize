package earth.terrarium.vitalize.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record BeheadingData(ResourceLocation id, EntityType<?> entity, ItemStack head) implements CodecRecipe<Container> {
    public static Codec<BeheadingData> codec(ResourceLocation id) {
        return RecordCodecBuilder.create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                Registry.ENTITY_TYPE.byNameCodec().fieldOf("entity").forGetter(BeheadingData::entity),
                ItemStackCodec.CODEC.fieldOf("head").forGetter(BeheadingData::head)
        ).apply(instance, BeheadingData::new));
    }

    @Override
    public boolean matches(@NotNull Container container, @NotNull Level level) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return VitalizeRecipes.BEHEADING_DATA_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return VitalizeRecipes.BEHEADING_DATA.get();
    }

    public static ItemStack getHead(ServerLevel level, EntityType<?> entity) {
        return level.getRecipeManager().getAllRecipesFor(VitalizeRecipes.BEHEADING_DATA.get()).stream().filter(recipe -> recipe.entity() == entity).findFirst().map(BeheadingData::head).orElse(ItemStack.EMPTY);
    }
}
