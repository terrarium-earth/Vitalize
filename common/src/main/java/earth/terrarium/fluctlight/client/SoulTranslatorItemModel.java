package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.item.SoulTranslatorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SoulTranslatorItemModel extends AnimatedGeoModel<SoulTranslatorItem> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Fluctlight.MODID, "geo/soul_translator.geo.json");
    public static final ResourceLocation SOUL_TRANSLATOR_TEXTURE = new ResourceLocation(Fluctlight.MODID, "textures/block/soul_translator.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Fluctlight.MODID, "animations/soul_translator.animation.json");

    @Override
    public ResourceLocation getModelResource(SoulTranslatorItem object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SoulTranslatorItem object) {
        return SOUL_TRANSLATOR_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(SoulTranslatorItem animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
