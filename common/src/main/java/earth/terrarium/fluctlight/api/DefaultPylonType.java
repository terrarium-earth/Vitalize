package earth.terrarium.fluctlight.api;

import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.recipes.BeheadingData;
import earth.terrarium.fluctlight.registry.FluctlightItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.utils.SoulUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.Locale;

public enum DefaultPylonType implements PylonType {
    BEHEADING(3, 1.45) {
        @Override
        public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
            var head = BeheadingData.getHead(serverLevel, entity);
            if(!head.isEmpty() && serverLevel.random.nextFloat() < 0.25f) {
                lootTable.add(head);
            }
            return lootTable;
        }
    },
    EXPERIENCE(3, 1.25) {
        @Override
        public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
            if(serverLevel.random.nextFloat() < 0.25f) {
                lootTable.add(FluctlightItems.EXPERIENCE_CUBED.get().getDefaultInstance());
            }
            return lootTable;
        }
    },
    IGNITION(1, 1.075) {
        @Override
        public FluctlightLootContext modifyLootContext(FluctlightLootContext context) {
            context.getEntity().setSecondsOnFire(10);
            return context;
        }
    },
    LOOTING(3, 1.25) {
        @Override
        public FluctlightLootContext modifyLootContext(FluctlightLootContext context) {
            context.incrementLootingAmount();
            return super.modifyLootContext(context);
        }
    },
    RECURSIVE(1, 1.5) {
        @Override
        public void onEnd(SoulTranslatorBlockEntity core) {
            if(core.getLevel() != null && core.getLevel().random.nextFloat() < 0.15) {
                SoulUtils.deviateSoulCount(core.getCrystal(), 1, core.getLevel(), null);
            }
        }
    },
    SPEED(5, 1.25) {
        @Override
        public void onStart(SoulTranslatorBlockEntity core) {
            core.setMaxTickTime((int) Math.max(1, core.getMaxTickTime() * 0.85F));
            super.onStart(core);
        }
    },
    EFFICIENCY(5, 0.85),
    NONE(8, 1);

    private final int maxLevel;
    private final double energyModifier;
    public final String name;
    DefaultPylonType(int maxLevel, double energyModifier) {
        this.maxLevel = maxLevel;
        this.energyModifier = energyModifier;
        this.name = "pylon_" + this.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public ResourceLocation getId() {
        return new ResourceLocation(Fluctlight.MODID, this.name().toLowerCase(Locale.ROOT));
    }

    @Override
    public int maxLevel() {
        return maxLevel;
    }

    @Override
    public double energyModifier() {
        return energyModifier;
    }
}
