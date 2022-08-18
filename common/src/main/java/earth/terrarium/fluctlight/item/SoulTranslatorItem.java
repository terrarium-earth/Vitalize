package earth.terrarium.fluctlight.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SoulTranslatorItem extends BlockItem implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public SoulTranslatorItem(Block block, Properties properties) {
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
