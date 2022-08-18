package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.api.DefaultPylonType;
import earth.terrarium.fluctlight.blocks.BasePylonBlock;
import earth.terrarium.fluctlight.blocks.BasePylonBlockEntity;
import earth.terrarium.fluctlight.item.PylonItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PylonItemModel extends AnimatedGeoModel<PylonItem> {
    public static final ResourceLocation SOUL_TRANSLATOR_MODEL = new ResourceLocation(Fluctlight.MODID, "geo/pylon.geo.json");
    public static final ResourceLocation DEFAULT_PYLON_TEXTURE = new ResourceLocation(Fluctlight.MODID, "textures/block/pylons/pylon_none.png");
    public static final ResourceLocation SOUL_TRANSLATOR_ANIMATION = new ResourceLocation(Fluctlight.MODID, "animations/pylon.animation.json");

    @Override
    public ResourceLocation getModelResource(PylonItem object) {
        return SOUL_TRANSLATOR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(PylonItem object) {
        Block block = object.getBlock();
        if(block instanceof BasePylonBlock pylon && pylon.getType() instanceof DefaultPylonType pylonType) {
            return new ResourceLocation(Fluctlight.MODID, "textures/block/pylons/" + pylonType.name + ".png");
        }
        return DEFAULT_PYLON_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(PylonItem animatable) {
        return SOUL_TRANSLATOR_ANIMATION;
    }
}
