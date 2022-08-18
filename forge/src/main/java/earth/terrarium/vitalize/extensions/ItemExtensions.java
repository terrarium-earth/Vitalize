package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.VitalizeItems;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@ExtensionFor(VitalizeItems.class)
public class ItemExtensions {
    
    @ExtensionImplementation
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return VitalizeForge.ITEMS.register(id, item);
    }
}
