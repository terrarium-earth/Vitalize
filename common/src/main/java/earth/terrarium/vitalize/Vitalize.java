package earth.terrarium.vitalize;

import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.registry.VitalizeItems;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import me.codexadrian.spirit.Spirit;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class Vitalize {
    public static final String MODID = "vitalize";
    public static final TagKey<EntityType<?>> BLACKLIST = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "revitalizer_blacklist"));

    public static void init() {
        VitalizeBlocks.init();
        VitalizeItems.init();
        VitalizeMenus.init();
        VitalizeRecipes.init();
    }

    public static void register() {
        VitalizeBlocks.register();
        VitalizeItems.register();
        VitalizeMenus.register();
        VitalizeRecipes.register();
    }
}