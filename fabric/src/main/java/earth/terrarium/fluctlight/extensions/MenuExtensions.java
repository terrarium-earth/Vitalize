package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.ExtraDataMenuProviderWrapper;
import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.registry.ExtraDataMenuProvider;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

@ExtensionFor(FluctlightMenus.class)
public class MenuExtensions {

    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        var registry = Registry.register(Registry.MENU, new ResourceLocation(Fluctlight.MODID, name), menu.get());
        return () -> registry;
    }

    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(FluctlightMenus.MenuFactory<T> factory) {
        return new ExtendedScreenHandlerType<>(factory::create);
    }

    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        player.openMenu(new ExtraDataMenuProviderWrapper(provider));
    }
}
