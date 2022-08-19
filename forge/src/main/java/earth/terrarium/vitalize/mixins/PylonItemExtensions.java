package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.ForgePylonRenderer;
import earth.terrarium.vitalize.item.PylonItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(value = PylonItem.class, remap = false)
public class PylonItemExtensions extends BlockItem {

    public PylonItemExtensions(Block arg, Properties arg2) {
        super(arg, arg2);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgePylonRenderer());
        super.initializeClient(consumer);
    }
}
