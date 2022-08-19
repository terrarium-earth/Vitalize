package earth.terrarium.vitalize.registry;

import earth.terrarium.vitalize.item.CubedExperience;
import me.codexadrian.spirit.Spirit;
import net.minecraft.world.item.Item;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class VitalizeItems {

    public static final Supplier<Item> EXPERIENCE_CUBED = register("experience_cubed", () -> new CubedExperience(new Item.Properties().tab(Spirit.SPIRIT)));

    public static Supplier<Item> register(String id, Supplier<Item> item) {
        throw new NotImplementedException("Item Registration ain't implemented");
    }

    public static void register() {

    }
}
