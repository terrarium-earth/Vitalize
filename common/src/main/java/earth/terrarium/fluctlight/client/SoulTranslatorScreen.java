package earth.terrarium.fluctlight.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.blocks.SoulTranslatorBlockEntity;
import earth.terrarium.fluctlight.blocks.SoulTranslatorMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class SoulTranslatorScreen extends AbstractContainerScreen<SoulTranslatorMenu> {
    private static final ResourceLocation BASE_SCREEN = new ResourceLocation(Fluctlight.MODID, "textures/gui/spiritulization_core_gui.png");

    public SoulTranslatorScreen(SoulTranslatorMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.imageWidth = 208;
        this.imageHeight = 225;
        this.inventoryLabelX = 23;
        this.inventoryLabelY = 128;
    }


    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float f, int i, int j) {
        RenderSystem.setShaderTexture(0, BASE_SCREEN);
        RenderSystem.setShaderColor(1, 1, 1, 1f);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        int energyLevel = this.getMenu().data.get(SoulTranslatorBlockEntity.ENERGY);
        int maxEnergy = this.getMenu().data.get(SoulTranslatorBlockEntity.MAX_ENERGY);
        int barHeight = Mth.clamp((int) (58F * (energyLevel / (float) maxEnergy)), 0, 58);
        blit(poseStack, leftPos + 186, topPos + 40 + (58 - barHeight), 208, 0, 12, barHeight);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int i, int j, float f) {
        this.renderBackground(poseStack);
        super.render(poseStack, i, j, f);
        renderTooltip(poseStack, i, j);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int i, int j) {
        this.font.draw(poseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 0xff03f2ff);
        this.font.draw(poseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 0xff03f2ff);
    }
}
