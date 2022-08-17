package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.recipes.BeheadingData;
import earth.terrarium.fluctlight.registry.FluctlightRecipes;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

@ExtensionFor(FluctlightRecipes.class)
public class RecipeExtensions {

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Fluctlight.MODID, name), recipe.get());
        return () -> register;
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Fluctlight.MODID, name), recipe.get());
        return () -> register;
    }
}
