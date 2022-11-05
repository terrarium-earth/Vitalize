package earth.terrarium.vitalize.api;

import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.recipes.BeheadingData;
import earth.terrarium.vitalize.registry.VitalizeItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.codexadrian.spirit.entity.EntityRarity;
import me.codexadrian.spirit.utils.SoulUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Locale;

public enum DefaultPylonType implements PylonType {
    BEHEADING(3, 1.45) {
        @Override
        public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
            var head = BeheadingData.getHead(serverLevel, entity);
            if(!head.isEmpty() && serverLevel.random.nextFloat() < 0.05f) {
                lootTable.add(head);
            }
            return lootTable;
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info", 5)
            );
        }
    },
    EXPERIENCE(3, 1.25) {
        @Override
        public ObjectArrayList<ItemStack> modifyLootTable(ObjectArrayList<ItemStack> lootTable, ServerLevel serverLevel, EntityType<?> entity) {
            EntityRarity rarity = EntityRarity.getRarity(entity);
            if(serverLevel.random.nextFloat() < 0.05) {
                lootTable.add(new ItemStack(VitalizeItems.EXPERIENCE_CUBED.get(), rarity.experienceDrops < 2 ? 1 : serverLevel.random.nextInt(rarity.experienceDrops/2, rarity.experienceDrops)));
            }
            return lootTable;
        }

        @Override
        public void onEnd(ObjectArrayList<ItemStack> drops, SoulRevitalizerBlockEntity core) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack itemStack = drops.get(i);
                if(itemStack.is(VitalizeItems.EXPERIENCE_SQUARED.get())) {
                    drops.set(i, new ItemStack(VitalizeItems.EXPERIENCE_CUBED.get(), itemStack.getCount()));
                } else if(itemStack.is(VitalizeItems.EXPERIENCE.get())) {
                    drops.set(i, new ItemStack(VitalizeItems.EXPERIENCE_SQUARED.get(), itemStack.getCount()));
                }
            }
            super.onEnd(drops, core);
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info_one"),
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info_two", 5),
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info_three")
            );
        }
    },
    FLAME(1, 1.075) {
        @Override
        public ModifiedLootContext modifyLootContext(ModifiedLootContext context) {
            context.getEntity().setSecondsOnFire(10);
            return context;
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info")
            );
        }
    },
    LOOTING(3, 1.25) {
        @Override
        public ModifiedLootContext modifyLootContext(ModifiedLootContext context) {
            context.incrementLootingAmount();
            return super.modifyLootContext(context);
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info", 1)
            );
        }
    },
    RECURSIVE(1, 1.5) {
        @Override
        public void onEnd(ObjectArrayList<ItemStack> drops, SoulRevitalizerBlockEntity core) {
            if(core.getLevel() != null && core.getLevel().random.nextFloat() < 0.15) {
                SoulUtils.deviateSoulCount(core.getCrystal(), 1, core.getLevel(), null);
            }
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info_one", 15),
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info_two", 1)
            );
        }
    },
    SPEED(5, 1.25) {
        @Override
        public void onStart(SoulRevitalizerBlockEntity core) {
            core.setMaxTickTime((int) Math.max(1, core.getMaxTickTime() * 0.85));
            super.onStart(core);
        }

        @Override
        public List<Component> description() {
            return List.of(
                    Component.translatable("block." + Vitalize.MODID + "." + this.name + ".info", 15)
            );
        }
    },
    EFFICIENCY(5, 0.85) {
        @Override
        public List<Component> description() {
            return List.of();
        }
    },
    BASE(8, 1) {
        @Override
        public List<Component> description() {
            return List.of();
        }
    };

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
        return new ResourceLocation(Vitalize.MODID, this.name().toLowerCase(Locale.ROOT));
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
