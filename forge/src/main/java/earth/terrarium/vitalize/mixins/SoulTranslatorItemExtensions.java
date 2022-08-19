package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.ForgeSoulRevitalizerRenderer;
import earth.terrarium.vitalize.item.SoulRevitalizerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(value = SoulRevitalizerItem.class, remap = false)
public class SoulTranslatorItemExtensions extends BlockItem {

    public SoulTranslatorItemExtensions(Block arg, Properties arg2) {
        super(arg, arg2);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgeSoulRevitalizerRenderer());
    }
}
