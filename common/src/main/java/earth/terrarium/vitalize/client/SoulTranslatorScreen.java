package earth.terrarium.vitalize.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamresourceful.resourcefullib.client.components.selection.SelectionList;
import com.teamresourceful.resourcefullib.client.utils.RenderUtils;
import earth.terrarium.vitalize.Vitalize;
import earth.terrarium.vitalize.api.EntityRarity;
import earth.terrarium.vitalize.blocks.SoulRevitalizerBlockEntity;
import earth.terrarium.vitalize.blocks.SoulRevitalizerMenu;
import earth.terrarium.vitalize.client.widgets.NonSelectableTextComponent;
import me.codexadrian.spirit.data.Tier;
import me.codexadrian.spirit.registry.SpiritItems;
import me.codexadrian.spirit.utils.SoulUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SoulTranslatorScreen extends AbstractContainerScreen<SoulRevitalizerMenu> {
    private static final ResourceLocation BASE_SCREEN = new ResourceLocation(Vitalize.MODID, "textures/gui/soul_revitalizer_gui.png");
    public Optional<Entity> entity = Optional.empty();
    private Tier tier;
    private SelectionList boxComponents;
    @Nullable
    private ItemStack crystal;

    public SoulTranslatorScreen(SoulRevitalizerMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.imageWidth = 208;
        this.imageHeight = 202;
        this.inventoryLabelX = 23;
        this.inventoryLabelY = 106;
        abstractContainerMenu.addSlotListener(new ContainerListener() {
            @Override
            public void slotChanged(@NotNull AbstractContainerMenu abstractContainerMenu, int i, @NotNull ItemStack itemStack) {}
            @Override
            public void dataChanged(@NotNull AbstractContainerMenu abstractContainerMenu, int i, int j) {
                SoulTranslatorScreen.this.entity = Optional.empty();
                SoulTranslatorScreen.this.tier = null;
            }
        });
    }

    @Override
    protected void init() {
        super.init();
        boxComponents = addRenderableWidget(new SelectionList(leftPos + 86, topPos + 20, 77, 80, 10, entry -> {}));
        boxComponents.updateEntries(baseComponents());
    }

    @Override
    protected void containerTick() {
        ItemStack item = this.getMenu().getItems().get(0);
        if(crystal != item) {
            if (item.isEmpty()) {
                crystal = ItemStack.EMPTY;
                entity = Optional.empty();
                boxComponents.updateEntries(baseComponents());
            } else {
                this.crystal = item;
                List<NonSelectableTextComponent> entries = getComponentsFromTier(getOrCreateTier()).stream().map(NonSelectableTextComponent::new).collect(Collectors.toList());
                List<NonSelectableTextComponent> components = baseComponents();
                if(!components.isEmpty()) entries.add(new NonSelectableTextComponent(Component.literal("---------------").withStyle(ChatFormatting.STRIKETHROUGH).withStyle(ChatFormatting.GRAY)));
                entries.addAll(components);
                this.boxComponents.updateEntries(entries);
                getOrCreateEntity();
            }
        }
    }

    @Override
    protected void renderBg(@NotNull PoseStack matrixStack, float f, int i, int j) {
        RenderSystem.setShaderTexture(0, BASE_SCREEN);
        RenderSystem.setShaderColor(1, 1, 1, 1f);
        blit(matrixStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        int energyBarHeight = Mth.clamp((int) (58F * (getEnergyLevel() / (float) getMaxEnergyLevel())), 0, 58);
        blit(matrixStack, leftPos + 186, topPos + 19 + (58 - energyBarHeight), 208, 58 - energyBarHeight, 12, energyBarHeight);
        int tickBarHeight = Mth.clamp((int) (58F * (getTicks() / (float) getMaxTicks())), 0, 58);
        blit(matrixStack, leftPos + 172, topPos + 19 + (58 - tickBarHeight), 220, 0, 6, tickBarHeight);
        this.getOrCreateEntity().ifPresent(entity -> {
            try(var ignored = RenderUtils.createScissorBox(minecraft, matrixStack, leftPos + 7, topPos + 17, 73, 84)) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    float renderScale = 2;
                    float scaledSize = 20.0F / Math.max(entity.getBbWidth(), entity.getBbHeight());
                    entity.tickCount = mc.player.tickCount;
                    matrixStack.pushPose();
                    matrixStack.translate(34.0, 20.0F * renderScale + 25.0F, 0.5);
                    matrixStack.translate(leftPos + 7, topPos + 17, 1.0);
                    matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
                    matrixStack.translate(0.0, 0.0, 100.0);
                    matrixStack.scale(-scaledSize * renderScale, scaledSize * renderScale, 30.0F);
                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(45f));
                    EntityRenderDispatcher manager = mc.getEntityRenderDispatcher();
                    MultiBufferSource.BufferSource renderTypeBuffer = mc.renderBuffers().bufferSource();
                    manager.render(entity, 0.0, 0.0, 0.0, mc.getFrameTime(), 1.0F, matrixStack, renderTypeBuffer, 15728880);
                    renderTypeBuffer.endBatch();
                    matrixStack.popPose();
                }
            }
        });
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
        if(mouseX > leftPos + 183 && mouseX < 200 + leftPos && mouseY > 17 + topPos && mouseY < 79 + topPos) {
            this.renderTooltip(poseStack, Component.translatable("gui." + Vitalize.MODID + ".energy_tooltip", Component.literal(String.valueOf(getEnergyLevel())).withStyle(ChatFormatting.GOLD), Component.literal(String.valueOf(getMaxEnergyLevel())).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.AQUA), mouseX, mouseY);
        } else if(mouseX > leftPos + 169 && mouseX < 180 + leftPos && mouseY > 17 + topPos && mouseY < 79 + topPos) {
            this.renderTooltip(poseStack, Component.translatable("gui." + Vitalize.MODID + ".work_tooltip", Component.literal(String.valueOf(getTicks())).withStyle(ChatFormatting.GOLD), Component.literal(String.valueOf(getMaxTicks())).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.AQUA), mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int i, int j) {
        this.font.draw(poseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 0x333333);
        this.font.draw(poseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 0x333333);
    }

    public int getEnergyLevel() {
        return this.getMenu().data.get(SoulRevitalizerBlockEntity.ENERGY);
    }

    public int getMaxEnergyLevel() {
        return this.getMenu().data.get(SoulRevitalizerBlockEntity.MAX_ENERGY);
    }

    public int getTicks() {
        return this.getMenu().data.get(SoulRevitalizerBlockEntity.TICK_TIME);
    }

    public int getMaxTicks() {
        return this.getMenu().data.get(SoulRevitalizerBlockEntity.MAX_TICK_TIME);
    }

    public int getEnergyConsumption() {
        return this.getMenu().data.get(SoulRevitalizerBlockEntity.ENERGY_CONSUMPTION);
    }

    public Optional<Entity> getOrCreateEntity() {
        if(entity.isEmpty() && crystal != null) {
            if(crystal.is(SpiritItems.SOUL_CRYSTAL.get())) {
                String entityName = SoulUtils.getSoulCrystalType(crystal);
                if (entityName != null) {
                    return this.entity = EntityType.byString(entityName).map(entityType -> entityType.create(minecraft.level));
                }
            }
        }
        return this.entity;
    }

    @Nullable
    public Tier getOrCreateTier() {
        if(tier == null && crystal != null) {
            if(crystal.is(SpiritItems.SOUL_CRYSTAL.get())) {
                return tier = SoulUtils.getTier(crystal, minecraft.level);
            }
        }
        return this.tier;
    }

    private List<Supplier<Component>> getComponentsFromTier(Tier tier) {
        List<Supplier<Component>> components = new ArrayList<>();
        if(tier != null) {
            getOrCreateEntity().ifPresent(entity -> components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".summoning", entity.getType().getDescription(), tier.displayName())));
            getOrCreateEntity().ifPresent(entity -> {
                EntityRarity rarity = EntityRarity.getRarity(entity.getType());
                components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".rarity", rarity.getTranslation().copy().withStyle(ChatFormatting.AQUA)).withStyle(rarity.color));
            });
            getOrCreateEntity().ifPresent(entity -> components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".tier", Component.translatable(tier.displayName()))));
            components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".consuming", this.getEnergyConsumption()));
            components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".work_time", getMaxTicks() / 20));
            components.add(() -> Component.translatable("gui." + Vitalize.MODID + ".spawn_count", tier.spawnCount()));
        }
        return components;
    }

    private List<NonSelectableTextComponent> baseComponents() {
        List<NonSelectableTextComponent> components = new ArrayList<>();
        if(this.getMenu().missingPylons) components.add(new NonSelectableTextComponent(Component.translatable("gui." + Vitalize.MODID + ".missing_pylons").withStyle(ChatFormatting.RED)));
        if(this.getMenu().missingInventory) components.add(new NonSelectableTextComponent(Component.translatable("gui." + Vitalize.MODID + ".missing_inventory").withStyle(ChatFormatting.RED)));
        return components;
    }
}
