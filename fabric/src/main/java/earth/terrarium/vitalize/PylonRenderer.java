package earth.terrarium.vitalize;

import earth.terrarium.vitalize.blocks.BasePylonBlockEntity;
import earth.terrarium.vitalize.client.PylonModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PylonRenderer extends GeoBlockRenderer<BasePylonBlockEntity> {

    public PylonRenderer() {
        super(new PylonModel());
    }
}
