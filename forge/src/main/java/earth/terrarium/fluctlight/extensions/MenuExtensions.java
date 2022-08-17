package earth.terrarium.fluctlight.extensions;

import earth.terrarium.fluctlight.FluctlightForge;
import earth.terrarium.fluctlight.registry.ExtraDataMenuProvider;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import earth.terrarium.fluctlight.util.extensions.ExtensionFor;
import earth.terrarium.fluctlight.util.extensions.ExtensionImplementation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

@ExtensionFor(FluctlightMenus.class)
public class MenuExtensions {

    @ExtensionImplementation
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        return FluctlightForge.MENU_TYPES.register(name, menu);
    }

    @ExtensionImplementation
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(FluctlightMenus.MenuFactory<T> factory) {
        return IForgeMenuType.create(factory::create);
    }

    @ExtensionImplementation
    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        NetworkHooks.openScreen(player, provider);
    }
}
