package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.client.SoulTranslatorModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SoulTranslatorRenderer extends GeoBlockRenderer<SoulTranslatorBlockEntity> {

    public SoulTranslatorRenderer() {
        super(new SoulTranslatorModel());
    }
}
