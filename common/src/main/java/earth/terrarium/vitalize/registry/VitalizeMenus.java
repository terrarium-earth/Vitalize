package earth.terrarium.vitalize.registry;

import earth.terrarium.botarium.api.registry.RegistryHelpers;
import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.blocks.SoulRevitalizerMenu;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class VitalizeMenus {
    public static final RegistryHolder<MenuType<?>> MENUS = new RegistryHolder<>(Registry.MENU, Vitalize.MODID);

    public static final Supplier<MenuType<SoulRevitalizerMenu>> SOUL_TRANSLATOR_MENU = MENUS.register("spiritulization_core", () -> RegistryHelpers.createMenuType(SoulRevitalizerMenu::new));

    public static void register() {
        MENUS.initialize();
    }
}
