package earth.terrarium.spiritualize;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.extensions.BlockExtensions;
import earth.terrarium.spiritualize.extensions.ItemExtensions;
import earth.terrarium.spiritualize.registry.SpiritualizeItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Spiritualize.MOD_ID)
public class SpiritualizeForge {
    public SpiritualizeForge() {
        Spiritualize.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemExtensions.ITEMS.register(modEventBus);
        BlockExtensions.BLOCKS.register(modEventBus);
        BlockExtensions.BLOCK_ENTITIES.register(modEventBus);
    }
}