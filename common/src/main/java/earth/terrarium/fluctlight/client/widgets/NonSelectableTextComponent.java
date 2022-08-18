package earth.terrarium.fluctlight.client.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamresourceful.resourcefullib.client.components.selection.ListEntry;
import com.teamresourceful.resourcefullib.client.scissor.ScissorBoxStack;
import com.teamresourceful.resourcefullib.common.utils.SelectableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class NonSelectableTextComponent extends ListEntry {
    private final Component data;

    public NonSelectableTextComponent(Component data) {
        this.data = data;
    }

    @Override
    protected void render(@NotNull ScissorBoxStack scissorBoxStack, @NotNull PoseStack stack, int id, int left, int top, int width, int height, int mouseX, int mouseY, boolean hovered, float partialTick, boolean selected) {
        Font font = Minecraft.getInstance().font;
        stack.pushPose();
        stack.translate(left, top, 0);
        stack.scale(0.85F, 0.85F, 1);
        GuiComponent.drawString(stack, font, this.data, 0, 1, 0xff6efffa);
        stack.popPose();
    }
}
