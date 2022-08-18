package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.ForgePylonRenderer;
import earth.terrarium.fluctlight.ForgeSoulTranslatorRenderer;
import earth.terrarium.fluctlight.item.PylonItem;
import earth.terrarium.fluctlight.item.SoulTranslatorItem;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

@ExtensionFor(SoulTranslatorItem.class)
public class SoulTranslatorItemExtensions {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgeSoulTranslatorRenderer());
    }
}
