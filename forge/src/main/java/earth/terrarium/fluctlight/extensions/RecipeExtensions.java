package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.FluctlightForge;
import earth.terrarium.fluctlight.recipes.BeheadingData;
import earth.terrarium.fluctlight.registry.FluctlightRecipes;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

@ExtensionFor(FluctlightRecipes.class)
public class RecipeExtensions {

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        return FluctlightForge.RECIPE_TYPES.register(name, recipe);
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        return FluctlightForge.RECIPE_SERIALIZERS.register(name, recipe);
    }
}
