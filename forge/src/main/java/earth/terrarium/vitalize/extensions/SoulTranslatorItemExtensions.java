package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.ForgeSoulRevitalizerRenderer;
import earth.terrarium.vitalize.item.SoulRevitalizerItem;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

@ExtensionFor(SoulRevitalizerItem.class)
public class SoulTranslatorItemExtensions {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgeSoulRevitalizerRenderer());
    }
}
