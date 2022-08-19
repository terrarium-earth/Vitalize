package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeRecipes.class, remap = false)
public class RecipeExtensions {

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Vitalize.MODID, name), recipe.get());
        return () -> register;
    }

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        var register = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Vitalize.MODID, name), recipe.get());
        return () -> register;
    }
}
