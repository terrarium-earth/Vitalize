package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.registry.SpiritualizeItems;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@ExtensionFor(SpiritualizeItems.class)
public class ItemExtensions {

    @ExtensionImplementation
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        var register = Registry.register(Registry.ITEM, new ResourceLocation(Spiritualize.MOD_ID, id), item.get());
        return () -> register;
    }
}
