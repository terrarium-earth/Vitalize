package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.client.SoulTranslatorScreen;
import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class FluctlightForgeClient {

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().register(FluctlightForgeClient.class);
        MenuScreens.register(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get(), SoulTranslatorRenderer::new);
        event.registerBlockEntityRenderer(FluctlightBlocks.PYLON_ENTITY.get(), PylonRenderer::new);
    }
}
