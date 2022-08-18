package earth.terrarium.vitalize.registry;

import earth.terrarium.vitalize.blocks.SoulRevitalizerMenu;
import earth.terrarium.vitalize.util.extensions.ExtensionDeclaration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class VitalizeMenus {

    public static final Supplier<MenuType<SoulRevitalizerMenu>> SOUL_TRANSLATOR_MENU = registerMenu("spiritulization_core", () -> createMenuType(SoulRevitalizerMenu::new));

    public static void register() {

    }

    @ExtensionDeclaration
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        throw new NotImplementedException();
    }

    @ExtensionDeclaration
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(MenuFactory<T> factory) {
        throw new NotImplementedException();
    }

    @ExtensionDeclaration
    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface MenuFactory<T extends AbstractContainerMenu> {
        T create(int syncId, Inventory inventory, FriendlyByteBuf byteBuf);
    }
}
