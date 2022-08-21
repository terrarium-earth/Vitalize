package earth.terrarium.vitalize.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExperienceItem extends Item {
    private final int experienceReward;
    public ExperienceItem(Properties properties, int experienceReward) {
        super(properties);
        this.experienceReward = experienceReward;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        if (level.isClientSide()) {
            return InteractionResultHolder.success(itemInHand);
        } else if(player.isShiftKeyDown()) {
            player.giveExperiencePoints(experienceReward * itemInHand.getCount());
            itemInHand.setCount(0);
            return InteractionResultHolder.consume(itemInHand);
        }
        player.giveExperiencePoints(experienceReward);
        itemInHand.shrink(1);
        return InteractionResultHolder.consume(itemInHand);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
}
