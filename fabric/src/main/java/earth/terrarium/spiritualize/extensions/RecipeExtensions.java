package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.recipes.BeheadingData;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

@ExtensionFor(BeheadingData.class)
public class RecipeExtensions {

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Spiritualize.MOD_ID, name), recipe.get());
        return () -> register;
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Spiritualize.MOD_ID, name), recipe.get());
        return () -> register;
    }
}
