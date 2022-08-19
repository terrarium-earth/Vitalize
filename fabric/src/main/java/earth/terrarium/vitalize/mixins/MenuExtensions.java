package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.ExtraDataMenuProviderWrapper;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.ExtraDataMenuProvider;
import earth.terrarium.vitalize.registry.VitalizeMenus;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeMenus.class, remap = false)
public class MenuExtensions {

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String name, Supplier<MenuType<T>> menu) {
        var registry = Registry.register(Registry.MENU, new ResourceLocation(Vitalize.MODID, name), menu.get());
        return () -> registry;
    }

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(VitalizeMenus.MenuFactory<T> factory) {
        return new ExtendedScreenHandlerType<>(factory::create);
    }

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static void openMenu(ServerPlayer player, ExtraDataMenuProvider provider) {
        player.openMenu(new ExtraDataMenuProviderWrapper(provider));
    }
}
