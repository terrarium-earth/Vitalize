package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.VitalizeItems;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@ExtensionFor(VitalizeItems.class)
public class ItemExtensions {

    @ExtensionImplementation
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        var register = Registry.register(Registry.ITEM, new ResourceLocation(Vitalize.MODID, id), item.get());
        return () -> register;
    }
}
