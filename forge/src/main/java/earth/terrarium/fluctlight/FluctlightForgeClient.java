package earth.terrarium.fluctlight;

import earth.terrarium.fluctlight.client.SoulTranslatorScreen;
import earth.terrarium.fluctlight.registry.FluctlightBlocks;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Fluctlight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FluctlightForgeClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        MenuScreens.register(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(FluctlightBlocks.SOUL_TRANSLATOR_ENTITY.get(), SoulTranslatorRenderer::new);
        event.registerBlockEntityRenderer(FluctlightBlocks.PYLON_ENTITY.get(), PylonRenderer::new);
    }
}
