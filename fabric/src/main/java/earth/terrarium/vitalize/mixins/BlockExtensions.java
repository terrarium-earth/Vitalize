package earth.terrarium.vitalize.mixins;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.registry.VitalizeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Supplier;

@Mixin(value = VitalizeBlocks.class, remap = false)
public class BlockExtensions {

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> item) {
        var register = Registry.register(Registry.BLOCK, new ResourceLocation(Vitalize.MODID, id), item.get());
        return () -> register;
    }

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <E extends BlockEntity, T extends BlockEntityType<E>> Supplier<T> registerBlockEntity(String id, Supplier<T> item) {
        var register = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(Vitalize.MODID, id), item.get());
        return () -> register;
    }

    /**
     * @author CodexAdrian
     * @reason because I said so
     */
    @Overwrite
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(VitalizeBlocks.BlockEntityFactory<E> factory, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory::create, blocks).build(null);
    }
}
