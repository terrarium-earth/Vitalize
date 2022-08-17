package earth.terrarium.fluctlight.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CubedExperience extends Item {
    public CubedExperience(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (level.isClientSide()) {
            return InteractionResultHolder.success(itemInHand);
        } else if(player.isShiftKeyDown()) {
            player.giveExperiencePoints(20 * itemInHand.getCount());
            itemInHand.setCount(0);
            return InteractionResultHolder.consume(itemInHand);
        }
        player.giveExperiencePoints(20);
        itemInHand.shrink(1);
        return InteractionResultHolder.consume(itemInHand);
    }
}
