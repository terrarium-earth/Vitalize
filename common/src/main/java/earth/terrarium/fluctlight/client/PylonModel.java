package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.api.DefaultPylonType;
import earth.terrarium.fluctlight.blocks.BasePylonBlock;
import earth.terrarium.fluctlight.blocks.BasePylonBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PylonModel extends AnimatedGeoModel<BasePylonBlockEntity> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Fluctlight.MODID, "geo/pylon.geo.json");
    public static final ResourceLocation DEFAULT_PYLON_TEXTURE = new ResourceLocation(Fluctlight.MODID, "textures/block/pylons/pylon.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Fluctlight.MODID, "animations/pylon.animation.json");

    @Override
    public ResourceLocation getModelResource(BasePylonBlockEntity object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(BasePylonBlockEntity object) {
        Block block = object.getBlockState().getBlock();
        if(block instanceof BasePylonBlock pylon && pylon.getType() instanceof DefaultPylonType pylonType) {
            return new ResourceLocation(Fluctlight.MODID, "textures/block/pylons/" + pylonType.name + ".png");
        }
        return DEFAULT_PYLON_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(BasePylonBlockEntity animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
