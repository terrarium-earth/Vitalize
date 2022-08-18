package earth.terrarium.fluctlight.blocks;

import com.mojang.datafixers.util.Pair;
import earth.terrarium.fluctlight.Fluctlight;
import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoulTranslatorMenu extends AbstractContainerMenu {
    private final Container container;
    public final ContainerData data;
    public final boolean missingPylons;
    public final boolean missingInventory;

    protected SoulTranslatorMenu(Container container, ContainerData data, int i, Inventory inventory, boolean missingPylons, boolean missingInventory) {
        super(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), i);
        this.container = container;
        this.data = data;
        this.missingPylons = missingPylons;
        this.missingInventory = missingInventory;
        this.addSlot(new Slot(this.container, 0, 83, 83));
        this.addSlot(new Slot(this.container, 1, 184, 83));
        addPlayerInvSlots(inventory);
        addDataSlots(data);
    }

    public SoulTranslatorMenu(int i, Inventory inventory, FriendlyByteBuf byteBuf) {
        this(new SimpleContainer(2), new SimpleContainerData(5), i, inventory, byteBuf.readBoolean(), byteBuf.readBoolean());
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotItem = slot.getItem();
            itemStack = slotItem.copy();

            if (index < 12) {
                if (!this.moveItemStackTo(slotItem, 12, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotItem, 0, 12, false)) {
                return ItemStack.EMPTY;
            }

            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return this.container.stillValid(player);
    }

    protected void addPlayerInvSlots(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 24 + j * 18, 120 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 24 + k * 18, 120 + 58));
        }
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
    }
}
