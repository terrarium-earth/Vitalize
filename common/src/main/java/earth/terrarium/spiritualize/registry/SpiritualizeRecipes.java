package earth.terrarium.spiritualize.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import earth.terrarium.spiritualize.recipes.BeheadingData;
import earth.terrarium.spiritualize.util.extensions.ExtensionDeclaration;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class SpiritualizeRecipes {

    public static final Supplier<RecipeType<BeheadingData>> BEHEADING_DATA = registerRecipeType("beheading_data", () -> new RecipeType<>(){
        @Override
        public String toString() {
            return "beheading_data";
        }
    });

    @ExtensionDeclaration
    public static final Supplier<RecipeSerializer<BeheadingData>> BEHEADING_DATA_SERIALIZER = registerRecipeSerializer("soul_engulfing", () -> new CodecRecipeSerializer<>(BEHEADING_DATA.get(), BeheadingData::codec));

    @ExtensionDeclaration
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        throw new NotImplementedException("Recipe Type Registration ain't implemented");
    }

    @ExtensionDeclaration
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        throw new NotImplementedException("Recipe Serializer Registration ain't implemented");
    }
}
