package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.client.PylonItemRenderer;
import earth.terrarium.fluctlight.client.SoulTranslatorItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ForgeSoulTranslatorRenderer implements IClientItemExtensions {
    private final BlockEntityWithoutLevelRenderer renderer = new SoulTranslatorItemRenderer();
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
    }
}
