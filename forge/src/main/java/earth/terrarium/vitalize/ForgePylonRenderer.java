package earth.terrarium.vitalize;

import earth.terrarium.vitalize.client.PylonItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ForgePylonRenderer implements IClientItemExtensions {
    private final BlockEntityWithoutLevelRenderer renderer = new PylonItemRenderer();
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
    }
}
