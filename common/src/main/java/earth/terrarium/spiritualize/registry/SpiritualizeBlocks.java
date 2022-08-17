package earth.terrarium.spiritualize.registry;

import earth.terrarium.spiritualize.api.DefaultPylonType;
import earth.terrarium.spiritualize.blocks.BasePylonBlock;
import earth.terrarium.spiritualize.blocks.BasePylonBlockEntity;
import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlock;
import earth.terrarium.spiritualize.blocks.SpiritualizationCoreBlockEntity;
import earth.terrarium.spiritualize.util.extensions.ExtensionDeclaration;
import me.codexadrian.spirit.Spirit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SpiritualizeBlocks {

    public static final Supplier<Block> PYLON_BLOCK = registerBlockWithItem("pylon", () -> new BasePylonBlock(DefaultPylonType.EFFICIENCY, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    //register pylons with all the default pylon types
    public static final Supplier<Block> PYLON_BLOCK_EFFICIENCY = registerBlockWithItem("pylon_efficiency", () -> new BasePylonBlock(DefaultPylonType.EFFICIENCY, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_BEHEADING = registerBlockWithItem("pylon_beheading", () -> new BasePylonBlock(DefaultPylonType.BEHEADING, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_LOOTING = registerBlockWithItem("pylon_looting", () -> new BasePylonBlock(DefaultPylonType.LOOTING, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_SPEED = registerBlockWithItem("pylon_speed", () -> new BasePylonBlock(DefaultPylonType.SPEED, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_EXPERIENCE = registerBlockWithItem("pylon_experience", () -> new BasePylonBlock(DefaultPylonType.EXPERIENCE, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_FLAME = registerBlockWithItem("pylon_flame", () -> new BasePylonBlock(DefaultPylonType.IGNITION, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<Block> PYLON_BLOCK_RECURSION = registerBlockWithItem("pylon_recursion", () -> new BasePylonBlock(DefaultPylonType.RECURSIVE, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));

    public static final Supplier<BlockEntityType<BasePylonBlockEntity>> PYLON_ENTITY = registerBlockEntity("pylon", () -> createBlockEntityType(BasePylonBlockEntity::new,
            PYLON_BLOCK.get(),
            PYLON_BLOCK_EFFICIENCY.get(),
            PYLON_BLOCK_BEHEADING.get(),
            PYLON_BLOCK_LOOTING.get(),
            PYLON_BLOCK_SPEED.get(),
            PYLON_BLOCK_EXPERIENCE.get(),
            PYLON_BLOCK_FLAME.get(),
            PYLON_BLOCK_RECURSION.get()
    ));
    public static final Supplier<Block> SPIRITULIZATION_CORE = registerBlockWithItem("spiritulization_core", () -> new SpiritualizationCoreBlock(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)));
    public static final Supplier<BlockEntityType<SpiritualizationCoreBlockEntity>> SPIRITUALIZATION_CORE_ENTITY = registerBlockEntity("spiritulization_core", () -> createBlockEntityType(SpiritualizationCoreBlockEntity::new, SPIRITULIZATION_CORE.get()));

    public static <T extends Block> Supplier<T> registerBlockWithItem(String id, Supplier<T> block) {
        var tempBlock = registerBlock(id, block);
        SpiritualizeItems.register(id, () -> new BlockItem(tempBlock.get(), new Item.Properties().tab(Spirit.SPIRIT)));
        return tempBlock;
    }

    @ExtensionDeclaration
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        throw new NotImplementedException("Block Registration ain't implemented");
    }

    @ExtensionDeclaration
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        throw new NotImplementedException("Block Entity Registration ain't implemented");
    }

    @ExtensionDeclaration
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityFactory<E> factory, Block... blocks) {
        throw new NotImplementedException("Block Entity Creation ain't implemented");
    }

    public static void register() {

    }

    @FunctionalInterface
    public interface BlockEntityFactory<T extends BlockEntity> {
        @NotNull T create(BlockPos blockPos, BlockState blockState);
    }
}
