package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.ForgePylonRenderer;
import earth.terrarium.fluctlight.item.PylonItem;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

@ExtensionFor(PylonItem.class)
public class PylonItemExtensions {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgePylonRenderer());
    }
}
