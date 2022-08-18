package earth.terrarium.fluctlight.registry;

import earth.terrarium.fluctlight.api.DefaultPylonType;
import earth.terrarium.fluctlight.blocks.BasePylonBlock;
import earth.terrarium.fluctlight.blocks.BasePylonBlockEntity;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlock;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.item.PylonItem;
import earth.terrarium.fluctlight.item.SoulTranslatorItem;
import earth.terrarium.fluctlight.util.extensions.ExtensionDeclaration;
import me.codexadrian.spirit.Spirit;
import net.minecraft.core.BlockPos;
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

public class FluctlightBlocks {

    public static final Supplier<BasePylonBlock> PYLON_BLOCK = registerPylon(DefaultPylonType.NONE);
    //register pylons with all the default pylon types
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_EFFICIENCY =  registerPylon(DefaultPylonType.EFFICIENCY);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_BEHEADING = registerPylon(DefaultPylonType.BEHEADING);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_LOOTING = registerPylon(DefaultPylonType.LOOTING);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_SPEED = registerPylon(DefaultPylonType.SPEED);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_EXPERIENCE = registerPylon(DefaultPylonType.EXPERIENCE);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_FLAME = registerPylon(DefaultPylonType.IGNITION);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_RECURSION = registerPylon(DefaultPylonType.RECURSIVE);

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
    public static final Supplier<Block> SOUL_TRANSLATOR = registerSoulTranslator("soul_translator", () -> new SoulTranslatorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()));
    public static final Supplier<BlockEntityType<SoulTranslatorBlockEntity>> SOUL_TRANSLATOR_ENTITY = registerBlockEntity("soul_translator", () -> createBlockEntityType(SoulTranslatorBlockEntity::new, SOUL_TRANSLATOR.get()));

    public static Supplier<BasePylonBlock> registerPylon(DefaultPylonType type) {
        Supplier<BasePylonBlock> block = registerBlock(type.name, () -> new BasePylonBlock(type, BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).noOcclusion()));
        FluctlightItems.register(type.name, () -> new PylonItem(block.get(), new Item.Properties().tab(Spirit.SPIRIT)));
        return block;
    }

    public static <T extends Block> Supplier<T> registerSoulTranslator(String id, Supplier<T> block) {
        var tempBlock = registerBlock(id, block);
        FluctlightItems.register(id, () -> new SoulTranslatorItem(tempBlock.get(), new Item.Properties().tab(Spirit.SPIRIT)));
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
