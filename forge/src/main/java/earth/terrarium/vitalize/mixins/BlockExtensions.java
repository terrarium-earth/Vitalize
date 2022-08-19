package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeBlocks.class, remap = false)
public class BlockExtensions {

    @Overwrite
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        return VitalizeForge.BLOCKS.register(id, item);
    }

    @Overwrite
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        return VitalizeForge.BLOCK_ENTITIES.register(id, item);
    }

    @Overwrite
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(VitalizeBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
