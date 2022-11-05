package earth.terrarium.vitalize.registry;

import earth.terrarium.botarium.api.registry.RegistryHelpers;
import earth.terrarium.botarium.api.registry.RegistryHolder;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.api.DefaultPylonType;
import earth.terrarium.vitalize.blocks.BasePylonBlock;
import earth.terrarium.vitalize.blocks.BasePylonBlockEntity;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlock;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.item.PylonItem;
import earth.terrarium.vitalize.item.SoulRevitalizerItem;
import me.codexadrian.spirit.Spirit;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class VitalizeBlocks {

    public static final RegistryHolder<Block> BLOCKS = new RegistryHolder<>(Registry.BLOCK, Vitalize.MODID);
    public static final RegistryHolder<BlockEntityType<?>> BLOCK_ENTITIES = new RegistryHolder<>(Registry.BLOCK_ENTITY_TYPE, Vitalize.MODID);

    public static final Supplier<BasePylonBlock> PYLON_BLOCK = registerPylon(DefaultPylonType.BASE);
    //register pylons with all the default pylon types
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_EFFICIENCY =  registerPylon(DefaultPylonType.EFFICIENCY);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_BEHEADING = registerPylon(DefaultPylonType.BEHEADING);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_LOOTING = registerPylon(DefaultPylonType.LOOTING);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_SPEED = registerPylon(DefaultPylonType.SPEED);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_EXPERIENCE = registerPylon(DefaultPylonType.EXPERIENCE);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_FLAME = registerPylon(DefaultPylonType.FLAME);
    public static final Supplier<BasePylonBlock> PYLON_BLOCK_RECURSION = registerPylon(DefaultPylonType.RECURSIVE);

    public static final Supplier<BlockEntityType<BasePylonBlockEntity>> PYLON_ENTITY = BLOCK_ENTITIES.register("pylon", () -> RegistryHelpers.createBlockEntityType(BasePylonBlockEntity::new,
            PYLON_BLOCK.get(),
            PYLON_BLOCK_EFFICIENCY.get(),
            PYLON_BLOCK_BEHEADING.get(),
            PYLON_BLOCK_LOOTING.get(),
            PYLON_BLOCK_SPEED.get(),
            PYLON_BLOCK_EXPERIENCE.get(),
            PYLON_BLOCK_FLAME.get(),
            PYLON_BLOCK_RECURSION.get()
    ));
    public static final Supplier<Block> SOUL_REVITALIZER = registerSoulTranslator("soul_revitalizer", () -> new SoulRevitalizerBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).noOcclusion()));
    public static final Supplier<BlockEntityType<SoulRevitalizerBlockEntity>> SOUL_REVITALIZER_ENTITY = BLOCK_ENTITIES.register("soul_revitalizer", () -> RegistryHelpers.createBlockEntityType(SoulRevitalizerBlockEntity::new, SOUL_REVITALIZER.get()));

    public static Supplier<BasePylonBlock> registerPylon(DefaultPylonType type) {
        Supplier<BasePylonBlock> block = BLOCKS.register(type.name, () -> new BasePylonBlock(type, BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).noOcclusion()));
        VitalizeItems.ITEMS.register(type.name, () -> new PylonItem(block.get(), new Item.Properties().tab(Spirit.SPIRIT)));
        return block;
    }

    public static <T extends Block> Supplier<T> registerSoulTranslator(String id, Supplier<T> block) {
        var tempBlock = BLOCKS.register(id, block);
        VitalizeItems.ITEMS.register(id, () -> new SoulRevitalizerItem(tempBlock.get(), new Item.Properties().tab(Spirit.SPIRIT)));
        return tempBlock;
    }

    public static void init() {}

    public static void register() {
        BLOCKS.initialize();
        BLOCK_ENTITIES.initialize();
    }
}
