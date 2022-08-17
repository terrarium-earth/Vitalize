package earth.terrarium.spiritualize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.resources.ResourceLocation;

public class SpiritualizeFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ResourceLocation(Spiritualize.MOD_ID, "block/pylon/spiritualization_core_crystal")));
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ResourceLocation(Spiritualize.MOD_ID, "block/pylon/spiritualization_core_faces")));
    }
}
