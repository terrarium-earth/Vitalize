package earth.terrarium.spiritualize.blocks;

import earth.terrarium.spiritualize.api.PylonType;
import earth.terrarium.spiritualize.api.DefaultPylonType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasePylonBlock extends BaseEntityBlock {
    private final PylonType type;

    public BasePylonBlock(PylonType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new BasePylonBlockEntity(blockPos, blockState);
    }

    public PylonType getType() {
        return type;
    }
}
