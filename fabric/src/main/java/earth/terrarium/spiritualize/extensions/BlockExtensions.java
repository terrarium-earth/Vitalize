package earth.terrarium.spiritualize.extensions;

import earth.terrarium.spiritualize.Spiritualize;
import earth.terrarium.spiritualize.registry.SpiritualizeBlocks;
import earth.terrarium.spiritualize.util.extensions.ExtensionFor;
import earth.terrarium.spiritualize.util.extensions.ExtensionImplementation;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@ExtensionFor(SpiritualizeBlocks.class)
public class BlockExtensions {

    @ExtensionImplementation
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        var register = Registry.register(Registry.BLOCK, new ResourceLocation(Spiritualize.MOD_ID, id), item.get());
        return () -> register;
    }

    @ExtensionImplementation
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        var register = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Spiritualize.MOD_ID, id), item.get());
        return () -> register;
    }

    @ExtensionImplementation
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(SpiritualizeBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build(null);
    }
}
