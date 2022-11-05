package earth.terrarium.vitalize.registry;

import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.item.ExperienceItem;
import me.codexadrian.spirit.Spirit;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class VitalizeItems {
    public static final RegistryHolder<Item> ITEMS = new RegistryHolder<>(Registry.ITEM, Vitalize.MODID);


    public static final Supplier<Item> EXPERIENCE = ITEMS.register("experience", () -> new ExperienceItem(new Item.Properties().tab(Spirit.SPIRIT), 2));
    public static final Supplier<Item> EXPERIENCE_SQUARED = ITEMS.register("experience_squared", () -> new ExperienceItem(new Item.Properties().tab(Spirit.SPIRIT), 4));
    public static final Supplier<Item> EXPERIENCE_CUBED = ITEMS.register("experience_cubed", () -> new ExperienceItem(new Item.Properties().tab(Spirit.SPIRIT), 16));

    public static void init() {}

    public static void register() {
        ITEMS.initialize();
    }
}
