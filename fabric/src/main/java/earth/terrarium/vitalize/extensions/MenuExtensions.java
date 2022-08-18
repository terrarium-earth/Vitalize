package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.ExtraDataMenuProviderWrapper;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.ExtraDataMenuProvider;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

@ExtensionFor(VitalizeMenus.class)
public class MenuExtensions {

    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        var registry = Registry.register(Registry.MENU, new ResourceLocation(Vitalize.MODID, name), menu.get());
        return () -> registry;
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(VitalizeMenus.MenuFactory<T> factory) {
        return new ExtendedScreenHandlerType<>(factory::create);
    }

    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        player.openMenu(new ExtraDataMenuProviderWrapper(provider));
    }
}
