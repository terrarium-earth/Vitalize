package earth.terrarium.vitalize;

import earth.terrarium.vitalize.client.SoulTranslatorScreen;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Vitalize.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VitalizeForgeClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        MenuScreens.register(VitalizeMenus.SOUL_TRANSLATOR_MENU.get(), SoulTranslatorScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(VitalizeBlocks.SOUL_REVITALIZER_ENTITY.get(), SoulRevitalizerRenderer::new);
        event.registerBlockEntityRenderer(VitalizeBlocks.PYLON_ENTITY.get(), PylonRenderer::new);
    }
}
