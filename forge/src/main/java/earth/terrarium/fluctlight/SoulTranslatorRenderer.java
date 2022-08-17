package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.client.SoulTranslatorModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SoulTranslatorRenderer extends GeoBlockRenderer<SoulTranslatorBlockEntity> {

    public SoulTranslatorRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new SoulTranslatorModel());
    }
}
