package earth.terrarium.vitalize.item;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.api.DefaultPylonType;
import earth.terrarium.vitalize.blocks.BasePylonBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.text.DecimalFormat;
import java.util.List;

public class PylonItem extends BlockItem implements IAnimatable {

    private static final DecimalFormat FORMAT = new DecimalFormat();
    private final AnimationFactory factory = new AnimationFactory(this);

    public PylonItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable("block." + Vitalize.MODID + ".more_info", Component.translatable("block.vitalize.shift").withStyle(Screen.hasShiftDown() ? ChatFormatting.WHITE : ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            if (this.getBlock() instanceof BasePylonBlock block && block.getType() instanceof DefaultPylonType type) {
                list.add(Component.translatable("block." + Vitalize.MODID + ".max_pylons", type.maxLevel()).withStyle(ChatFormatting.GRAY));
                if(type.energyModifier() != 1) {
                    list.add(Component.translatable("block." + Vitalize.MODID + ".pylon_energy_modifier", type.energyModifier() >= 1 ? "+": "-", FORMAT.format(Math.abs((1 - type.energyModifier())) * 100D)).withStyle(ChatFormatting.GRAY));
                }
                list.addAll(type.description().stream().map(component -> component.copy().withStyle(ChatFormatting.GRAY)).toList());
            }
        }

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
