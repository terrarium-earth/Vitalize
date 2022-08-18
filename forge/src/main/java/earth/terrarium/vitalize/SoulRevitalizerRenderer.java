package earth.terrarium.vitalize;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.client.SoulTranslatorModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SoulRevitalizerRenderer extends GeoBlockRenderer<SoulRevitalizerBlockEntity> {

    public SoulRevitalizerRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new SoulTranslatorModel());
    }
}
