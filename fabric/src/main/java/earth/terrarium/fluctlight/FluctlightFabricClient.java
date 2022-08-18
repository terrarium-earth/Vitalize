package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.client.PylonItemRenderer;
import earth.terrarium.fluctlight.client.SoulTranslatorItemRenderer;
import earth.terrarium.fluctlight.client.SoulTranslatorScreen;
import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class FluctlightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(FluctlightBlocks.PYLON_ENTITY.get(), (rendererDispatcherIn) -> new PylonRenderer());
        BlockEntityRendererRegistry.register(FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get(), (rendererDispatcherIn) -> new SoulTranslatorRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.SOUL_TRANSLATOR.get().asItem(), new SoulTranslatorItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_BEHEADING.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_EFFICIENCY.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_FLAME.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_EXPERIENCE.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_LOOTING.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_RECURSION.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(FluctlightBlocks.PYLON_BLOCK_SPEED.get().asItem(), new PylonItemRenderer());
        MenuScreens.register(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }
}
