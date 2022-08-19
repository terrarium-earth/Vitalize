package earth.terrarium.vitalize;

import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.registry.VitalizeItems;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import earth.terrarium.vitalize.registry.VitalizeRecipes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class Vitalize {
    public static final String MODID = "vitalize";
    public static final TagKey<EntityType<?>> COMMON = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "rarity/common"));
    public static final TagKey<EntityType<?>> UNCOMMON = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "rarity/uncommon"));
    public static final TagKey<EntityType<?>> RARE = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "rarity/rare"));
    public static final TagKey<EntityType<?>> EPIC = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "rarity/epic"));
    public static final TagKey<EntityType<?>> LEGENDARY = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(MODID, "rarity/legendary"));

    public static void init() {
        VitalizeBlocks.register();
        VitalizeItems.register();
        VitalizeMenus.register();
        VitalizeRecipes.register();
    }
}