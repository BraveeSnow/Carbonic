package world.snows.forever.carbonic.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.commons.math3.exception.OutOfRangeException;
import world.snows.forever.carbonic.machine.entity.ElectrolysisTankBlockEntity;
import world.snows.forever.carbonic.registry.BlockRegistry;
import world.snows.forever.carbonic.registry.MenuRegistry;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@ParametersAreNonnullByDefault
public class
ElectrolysisTankMenu extends AbstractContainerMenu {
    private final Level level;
    private final ElectrolysisTankBlockEntity blockEntity;
    private final ContainerData data;

    public ElectrolysisTankMenu(int id, Inventory inv, FriendlyByteBuf data) {
        this(id, inv, Objects.requireNonNull(inv.player.level().getBlockEntity(data.readBlockPos())),
                new SimpleContainerData(ElectrolysisTankBlockEntity.DATA_SIZE));
    }

    public ElectrolysisTankMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuRegistry.ELECTROLYSIS_TANK_MENU.get(), id);

        checkContainerSize(inv, ElectrolysisTankBlockEntity.CONTAINER_SIZE);

        this.level = entity.getLevel();
        this.blockEntity = (ElectrolysisTankBlockEntity) entity;
        this.data = data;

        loadPlayerHotbar(inv);
        loadPlayerInventory(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
            this.addSlot(new SlotItemHandler(itemHandler, 0, 80, 62));
        });

        addDataSlots(data);
    }

    public boolean hasMembraneCell() {
        final AtomicBoolean membranePresent = new AtomicBoolean(false);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
            membranePresent.set(itemHandler.getStackInSlot(0) != ItemStack.EMPTY);
        });

        return membranePresent.get();
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        final Slot source = this.slots.get(index);

        if (!source.hasItem())
            return ItemStack.EMPTY;

        final ItemStack originalStack = source.getItem();
        final ItemStack stackCopy = originalStack.copy();

        if (index < Inventory.INVENTORY_SIZE) {
            if (!this.moveItemStackTo(stackCopy, Inventory.INVENTORY_SIZE, Inventory.INVENTORY_SIZE
                    + ElectrolysisTankBlockEntity.CONTAINER_SIZE, false))
                return ItemStack.EMPTY;
        } else if (index < Inventory.INVENTORY_SIZE + ElectrolysisTankBlockEntity.CONTAINER_SIZE) {
            if (!this.moveItemStackTo(stackCopy, 0, Inventory.INVENTORY_SIZE, false))
                return ItemStack.EMPTY;
        } else {
            throw new OutOfRangeException(index, 0, Inventory.INVENTORY_SIZE + ElectrolysisTankBlockEntity.CONTAINER_SIZE);
        }

        if (stackCopy.getCount() == 0) {
            source.set(ItemStack.EMPTY);
        } else {
            source.setChanged();
        }

        source.onTake(player, stackCopy);
        return originalStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(this.level, this.blockEntity.getBlockPos()), player,
                BlockRegistry.ELECTROLYSIS_TANK.get());
    }

    private void loadPlayerInventory(Inventory inv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, i * 9 + j + 9, j * 18 + 8, i * 18 + 84));
            }
        }
    }

    private void loadPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inv, i, i * 18 + 8, 142));
        }
    }
}
