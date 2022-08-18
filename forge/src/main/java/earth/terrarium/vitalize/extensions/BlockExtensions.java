package earth.terrarium.vitalize.extensions;

import earth.terrarium.vitalize.VitalizeForge;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import earth.terrarium.vitalize.util.extensions.ExtensionFor;
import earth.terrarium.vitalize.util.extensions.ExtensionImplementation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@ExtensionFor(VitalizeBlocks.class)
public class BlockExtensions {

    @ExtensionImplementation
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        return VitalizeForge.BLOCKS.register(id, item);
    }

    @ExtensionImplementation
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        return VitalizeForge.BLOCK_ENTITIES.register(id, item);
    }

    @ExtensionImplementation
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(VitalizeBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
