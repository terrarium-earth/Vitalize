package earth.terrarium.spiritualize.registry;

import earth.terrarium.spiritualize.item.CubedExperience;
import me.codexadrian.spirit.Spirit;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SpiritualizeItems {

    public static final Supplier<Item> EXPERIENCE_CUBED = register("experience_cubed", () -> new CubedExperience(new Item.Properties().tab(Spirit.SPIRIT)));

    public static Supplier<Item> register(String id, Supplier<Item> item) {
        throw new AssertionError();
    }

    public static void register() {

    }
}
