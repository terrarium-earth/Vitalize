package earth.terrarium.vitalize;

import earth.terrarium.vitalize.client.PylonItemRenderer;
import earth.terrarium.vitalize.client.SoulTranslatorItemRenderer;
import earth.terrarium.vitalize.client.SoulTranslatorScreen;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class VitalizeFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //BlockEntityRendererRegistry.register(VitalizeBlocks.PYLON_ENTITY.get(), (rendererDispatcherIn) -> new PylonRenderer());
        //BlockEntityRendererRegistry.register(VitalizeBlocks.SOUL_REVITALIZER_ENTITY.get(), (rendererDispatcherIn) -> new SoulVitalizerRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.SOUL_REVITALIZER.get().asItem(), new SoulTranslatorItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_BEHEADING.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_EFFICIENCY.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_FLAME.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_EXPERIENCE.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_LOOTING.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_RECURSION.get().asItem(), new PylonItemRenderer());
        GeoItemRenderer.registerItemRenderer(VitalizeBlocks.PYLON_BLOCK_SPEED.get().asItem(), new PylonItemRenderer());
        MenuScreens.register(VitalizeMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }
}
