package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.SpiritualizeForge;
import earth.terrarium.spiritualize.registry.SpiritualizeItems;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@ExtensionFor(SpiritualizeItems.class)
public class ItemExtensions {
    
    @ExtensionImplementation
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return SpiritualizeForge.ITEMS.register(id, item);
    }
}
