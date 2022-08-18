package earth.terrarium.vitalize.client;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.api.DefaultPylonType;
import earth.terrarium.vitalize.blocks.BasePylonBlock;
import earth.terrarium.vitalize.item.PylonItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PylonItemModel extends AnimatedGeoModel<PylonItem> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Vitalize.MODID, "geo/pylon.geo.json");
    public static final ResourceLocation DEFAULT_PYLON_TEXTURE = new ResourceLocation(Vitalize.MODID, "textures/block/pylons/pylon_base.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Vitalize.MODID, "animations/pylon.animation.json");

    @Override
    public ResourceLocation getModelResource(PylonItem object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(PylonItem object) {
        Block block = object.getBlock();
        if(block instanceof BasePylonBlock pylon && pylon.getType() instanceof DefaultPylonType pylonType) {
            return new ResourceLocation(Vitalize.MODID, "textures/block/pylons/" + pylonType.name + ".png");
        }
        return DEFAULT_PYLON_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(PylonItem animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
