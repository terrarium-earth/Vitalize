package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.client.SoulTranslatorScreen;
import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

public class FluctlightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(FluctlightBlocks.PYLON_ENTITY.get(), (rendererDispatcherIn) -> new PylonRenderer());
        BlockEntityRendererRegistry.register(FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get(), (rendererDispatcherIn) -> new SoulTranslatorRenderer());
        MenuScreens.register(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }
}
