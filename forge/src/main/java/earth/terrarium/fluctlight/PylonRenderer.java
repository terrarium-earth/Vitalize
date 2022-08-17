package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.blocks.BasePylonBlockEntity;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.client.PylonModel;
import earth.terrarium.fluctlight.client.SoulTranslatorModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PylonRenderer extends GeoBlockRenderer<BasePylonBlockEntity> {

    public PylonRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new PylonModel());
    }
}
