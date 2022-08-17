package earth.terrarium.spiritualize.blocks;

import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasePylonBlockEntity extends BlockEntity {
    public BasePylonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SpiritualizeBlocks.PYLON_ENTITY.get(), blockPos, blockState);
    }
}
