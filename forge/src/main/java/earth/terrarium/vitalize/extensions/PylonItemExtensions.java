package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.ForgePylonRenderer;
import earth.terrarium.vitalize.item.PylonItem;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

@ExtensionFor(PylonItem.class)
public class PylonItemExtensions {

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new ForgePylonRenderer());
    }
}
