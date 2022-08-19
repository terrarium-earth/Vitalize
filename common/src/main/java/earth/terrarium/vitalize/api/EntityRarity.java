package earth.terrarium.vitalize.api;

import earth.terrarium.vitalize.Vitalize;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;

import java.util.Locale;

public enum EntityRarity {
    COMMON(ChatFormatting.GRAY, 1, 1),
    UNCOMMON(ChatFormatting.GREEN, 2, 1.25),
    RARE(ChatFormatting.BLUE, 4, 1.5),
    EPIC(ChatFormatting.DARK_PURPLE, 8, 2),
    LEGENDARY(ChatFormatting.GOLD, 16, 4);

    public final ChatFormatting color;
    public final int experienceDrops;
    public final double energyModifer;

    EntityRarity(ChatFormatting chatFormatting, int experienceDrops, double energyModifier) {
        this.color = chatFormatting;
        this.experienceDrops = experienceDrops;
        this.energyModifer = energyModifier;
    }

    public static EntityRarity getRarity(EntityType<?> entity) {
        if(entity.is(Vitalize.UNCOMMON)) return UNCOMMON;
        else if(entity.is(Vitalize.RARE)) return RARE;
        else if(entity.is(Vitalize.EPIC)) return EPIC;
        else if(entity.is(Vitalize.LEGENDARY)) return LEGENDARY;
        return COMMON;
    }

    public Component getTranslation() {
        return Component.translatable("rarity." + Vitalize.MODID + "." + this.name().toLowerCase(Locale.ROOT));
    }
}
