package earth.terrarium.vitalize.client;

import earth.terrarium.vitalize.item.SoulRevitalizerItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SoulTranslatorItemRenderer extends GeoItemRenderer<SoulRevitalizerItem> {
    public SoulTranslatorItemRenderer() {
        super(new SoulTranslatorItemModel());
    }
}
