package earth.terrarium.vitalize.client;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SoulTranslatorModel extends AnimatedGeoModel<SoulRevitalizerBlockEntity> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Vitalize.MODID, "geo/soul_translator.geo.json");
    public static final ResourceLocation SOUL_TRANSLATOR_TEXTURE = new ResourceLocation(Vitalize.MODID, "textures/block/soul_translator.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Vitalize.MODID, "animations/soul_translator.animation.json");

    @Override
    public ResourceLocation getModelResource(SoulRevitalizerBlockEntity object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SoulRevitalizerBlockEntity object) {
        return SOUL_TRANSLATOR_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(SoulRevitalizerBlockEntity animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
