package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.VitalizeItems;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeItems.class, remap = false)
public class ItemExtensions {

    @Overwrite
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return VitalizeForge.ITEMS.register(id, item);
    }
}
