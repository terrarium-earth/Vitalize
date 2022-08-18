package earth.terrarium.vitalize.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SoulRevitalizerItem extends BlockItem implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public SoulRevitalizerItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
