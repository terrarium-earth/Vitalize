package earth.terrarium.vitalize.client;

import earth.terrarium.vitalize.item.PylonItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PylonItemRenderer extends GeoItemRenderer<PylonItem> {
    public PylonItemRenderer() {
        super(new PylonItemModel());
    }
}
