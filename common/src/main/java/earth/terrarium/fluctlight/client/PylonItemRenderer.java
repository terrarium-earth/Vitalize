package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.item.PylonItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PylonItemRenderer extends GeoItemRenderer<PylonItem> {
    public PylonItemRenderer() {
        super(new PylonItemModel());
    }
}
