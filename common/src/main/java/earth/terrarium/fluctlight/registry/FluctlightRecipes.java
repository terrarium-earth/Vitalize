package earth.terrarium.fluctlight.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import earth.terrarium.fluctlight.recipes.BeheadingData;
import earth.terrarium.fluctlight.recipes.SpecialDropsData;
import earth.terrarium.fluctlight.util.extensions.ExtensionDeclaration;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class FluctlightRecipes {

    public static final Supplier<RecipeType<BeheadingData>> BEHEADING_DATA = registerRecipeType("beheading_drops", () -> new RecipeType<>(){
        @Override
        public String toString() {
            return "beheading_drops";
        }
    });

    public static final Supplier<RecipeType<SpecialDropsData>> SPECIAL_DROPS = registerRecipeType("special_drops", () -> new RecipeType<>(){
        @Override
        public String toString() {
            return "special_drops";
        }
    });

    public static final Supplier<RecipeSerializer<BeheadingData>> BEHEADING_DATA_SERIALIZER = registerRecipeSerializer("beheading_drops", () -> new CodecRecipeSerializer<>(BEHEADING_DATA.get(), BeheadingData::codec));
    public static final Supplier<RecipeSerializer<SpecialDropsData>> SPECIAL_DROPS_SERIALIZER = registerRecipeSerializer("special_drops", () -> new CodecRecipeSerializer<>(SPECIAL_DROPS.get(), SpecialDropsData::codec));

    @ExtensionDeclaration
    public static <R extends Recipe<?>, T extends RecipeType<R>> Supplier<T> registerRecipeType(String name, Supplier<T> recipe) {
        throw new NotImplementedException("Recipe Type Registration ain't implemented");
    }

    @ExtensionDeclaration
    public static <R extends Recipe<?>, T extends RecipeSerializer<R>> Supplier<T> registerRecipeSerializer(String name, Supplier<T> recipe) {
        throw new NotImplementedException("Recipe Serializer Registration ain't implemented");
    }

    public static void register() {

    }
}
