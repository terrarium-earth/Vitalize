package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
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
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Spiritualize.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Spiritualize.MOD_ID);

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        return RECIPE_TYPES.register(name, recipe);
    }

    @ExtensionImplementation
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        return RECIPE_SERIALIZERS.register(name, recipe);
    }
}
