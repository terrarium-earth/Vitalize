package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.VitalizeItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeItems.class, remap = false)
public class ItemExtensions {

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static Supplier<Item> register(String id, Supplier<Item> item) {
        var register = Registry.register(Registry.ITEM, new ResourceLocation(Vitalize.MODID, id), item.get());
        return () -> register;
    }
}
