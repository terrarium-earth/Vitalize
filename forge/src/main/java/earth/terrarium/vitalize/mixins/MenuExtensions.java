package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.ExtraDataMenuProvider;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeMenus.class, remap = false)
public class MenuExtensions {

    @Overwrite
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        return VitalizeForge.MENU_TYPES.register(name, menu);
    }

    @Overwrite
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(VitalizeMenus.MenuFactory<T> factory) {
        return IForgeMenuType.create(factory::create);
    }

    @Overwrite
    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        NetworkHooks.openScreen(player, provider, (data) -> provider.writeExtraData(player, data));
    }
}
