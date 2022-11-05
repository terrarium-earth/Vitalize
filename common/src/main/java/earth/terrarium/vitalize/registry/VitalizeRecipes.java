package earth.terrarium.vitalize.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.recipes.BeheadingData;
import earth.terrarium.vitalize.recipes.SpecialDropsData;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class VitalizeRecipes {

    public static final RegistryHolder<RecipeType<?>> RECIPES = new RegistryHolder<>(Registry.RECIPE_TYPE, Vitalize.MODID);
    public static final RegistryHolder<RecipeSerializer<?>> RECIPE_SERIALIZERS = new RegistryHolder<>(Registry.RECIPE_SERIALIZER, Vitalize.MODID);

    public static final Supplier<RecipeType<BeheadingData>> BEHEADING_DATA = RECIPES.register("beheading_drops", () -> new RecipeType<>(){
        @Override
        public String toString() {
            return "beheading_drops";
        }
    });

    public static final Supplier<RecipeType<SpecialDropsData>> SPECIAL_DROPS = RECIPES.register("special_drops", () -> new RecipeType<>(){
        @Override
        public String toString() {
            return "special_drops";
        }
    });

    public static final Supplier<RecipeSerializer<BeheadingData>> BEHEADING_DATA_SERIALIZER = RECIPE_SERIALIZERS.register("beheading_drops", () -> new CodecRecipeSerializer<>(BEHEADING_DATA.get(), BeheadingData::codec));
    public static final Supplier<RecipeSerializer<SpecialDropsData>> SPECIAL_DROPS_SERIALIZER = RECIPE_SERIALIZERS.register("special_drops", () -> new CodecRecipeSerializer<>(SPECIAL_DROPS.get(), SpecialDropsData::codec));

    public static void register() {
        RECIPES.initialize();
        RECIPE_SERIALIZERS.initialize();
    }

    public static void init() {

    }
}
