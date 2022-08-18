package earth.terrarium.vitalize;

import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.client.SoulTranslatorModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SoulVitalizerRenderer extends GeoBlockRenderer<SoulRevitalizerBlockEntity> {

    public SoulVitalizerRenderer() {
        super(new SoulTranslatorModel());
    }
}
