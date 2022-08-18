package earth.terrarium.vitalize;

import earth.terrarium.vitalize.blocks.BasePylonBlockEntity;
import earth.terrarium.vitalize.client.PylonModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PylonRenderer extends GeoBlockRenderer<BasePylonBlockEntity> {

    public PylonRenderer(BlockEntityRendererProvider.Context context) {
        super(context, new PylonModel());
    }
}
