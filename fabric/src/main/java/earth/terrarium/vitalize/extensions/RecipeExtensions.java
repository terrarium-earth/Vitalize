package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

@ExtensionFor(VitalizeRecipes.class)
public class RecipeExtensions {

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vitalize.MODID, name), recipe.get());
        return () -> register;
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Vitalize.MODID, name), recipe.get());
        return () -> register;
    }
}
