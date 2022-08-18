package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.ExtraDataMenuProvider;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

@ExtensionFor(VitalizeMenus.class)
public class MenuExtensions {

    @ExtensionImplementation
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        return VitalizeForge.MENU_TYPES.register(name, menu);
    }

    @ExtensionImplementation
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(VitalizeMenus.MenuFactory<T> factory) {
        return IForgeMenuType.create(factory::create);
    }

    @ExtensionImplementation
    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        NetworkHooks.openScreen(player, provider, (data) -> provider.writeExtraData(player, data));
    }
}
