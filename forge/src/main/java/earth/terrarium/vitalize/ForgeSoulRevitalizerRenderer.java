package earth.terrarium.vitalize;

import earth.terrarium.vitalize.client.SoulTranslatorItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ForgeSoulRevitalizerRenderer implements IClientItemExtensions {
    private final BlockEntityWithoutLevelRenderer renderer = new SoulTranslatorItemRenderer();
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
    }
}
