package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SoulTranslatorModel extends AnimatedGeoModel<SoulTranslatorBlockEntity> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Fluctlight.MODID, "geo/soul_translator.geo.json");
    public static final ResourceLocation SOUL_TRANSLATOR_TEXTURE = new ResourceLocation(Fluctlight.MODID, "textures/block/soul_translator.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Fluctlight.MODID, "animations/soul_translator.animation.json");

    @Override
    public ResourceLocation getModelResource(SoulTranslatorBlockEntity object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SoulTranslatorBlockEntity object) {
        return SOUL_TRANSLATOR_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(SoulTranslatorBlockEntity animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
