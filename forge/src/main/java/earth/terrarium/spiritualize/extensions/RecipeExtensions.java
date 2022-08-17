package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.SpiritualizeForge;
import earth.terrarium.spiritualize.recipes.BeheadingData;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@ExtensionFor(BeheadingData.class)
public class RecipeExtensions {

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        return SpiritualizeForge.RECIPE_TYPES.register(name, recipe);
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        return SpiritualizeForge.RECIPE_SERIALIZERS.register(name, recipe);
    }
}
