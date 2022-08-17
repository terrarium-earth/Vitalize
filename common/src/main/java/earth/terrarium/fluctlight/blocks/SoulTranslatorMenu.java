package earth.terrarium.fluctlight.blocks;

import earth.terrarium.fluctlight.registry.FluctlightMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SoulTranslatorMenu extends AbstractContainerMenu {
    private final Container container;
    public final ContainerData data;
    private final BlockPos pos;

    protected SoulTranslatorMenu(Container container, ContainerData data, int i, Inventory inventory, BlockPos pos) {
        super(FluctlightMenus.SOUL_TRANSLATOR_MENU.get(), i);
        this.container = container;
        this.data = data;
        this.pos = pos;
        this.addSlot(new Slot(this.container, 0, 184, 18));
        this.addSlot(new Slot(this.container, 1, 184, 104));
        addPlayerInvSlots(inventory);
        addDataSlots(data);
    }

    public SoulTranslatorMenu(int i, Inventory inventory, FriendlyByteBuf byteBuf) {
        this(new SimpleContainer(2), new SimpleContainerData(4), i, inventory, byteBuf.readBlockPos());
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
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 24 + j * 18, 143 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 24 + k * 18, 143 + 58));
        }
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
    }
}
