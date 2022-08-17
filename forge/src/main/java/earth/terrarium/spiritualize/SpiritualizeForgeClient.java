package earth.terrarium.spiritualize;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;

public class SpiritualizeForgeClient {

    public static void onModelLoad(ModelEvent.RegisterAdditional event) {
        event.register(new ResourceLocation(Spiritualize.MOD_ID, "block/pylon/spiritualization_core_crystal"));
        event.register(new ResourceLocation(Spiritualize.MOD_ID, "block/pylon/spiritualization_core_faces"));
    }
}
