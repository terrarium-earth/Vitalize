package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.FluctlightForge;
import earth.terrarium.fluctlight.registry.FluctlightItems;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

@ExtensionFor(FluctlightItems.class)
public class ItemExtensions {
    
    @ExtensionImplementation
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return FluctlightForge.ITEMS.register(id, item);
    }
}
