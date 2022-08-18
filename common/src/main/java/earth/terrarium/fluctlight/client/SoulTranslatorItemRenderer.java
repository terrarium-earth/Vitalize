package earth.terrarium.fluctlight.client;

import earth.terrarium.fluctlight.item.SoulTranslatorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SoulTranslatorItemRenderer extends GeoItemRenderer<SoulTranslatorItem> {
    public SoulTranslatorItemRenderer() {
        super(new SoulTranslatorItemModel());
    }
}
