package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.SpiritualizeForge;
import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@ExtensionFor(SpiritualizeBlocks.class)
public class BlockExtensions {

    @ExtensionImplementation
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        return SpiritualizeForge.BLOCKS.register(id, item);
    }

    @ExtensionImplementation
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        return SpiritualizeForge.BLOCK_ENTITIES.register(id, item);
    }

    @ExtensionImplementation
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(SpiritualizeBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::create, blocks).build(null);
    }
}
