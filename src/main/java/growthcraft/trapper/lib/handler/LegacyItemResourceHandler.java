package growthcraft.trapper.lib.handler;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

/** Transactional bridge while the menu inventory still uses the legacy slot API. */
@SuppressWarnings("removal")
public final class LegacyItemResourceHandler extends SnapshotJournal<NonNullList<ItemStack>>
        implements ResourceHandler<ItemResource> {
    private final IItemHandlerModifiable handler;

    public LegacyItemResourceHandler(IItemHandlerModifiable handler) {
        this.handler = handler;
    }

    @Override
    public int size() {
        return handler.getSlots();
    }

    @Override
    public ItemResource getResource(int index) {
        return ItemResource.of(handler.getStackInSlot(index));
    }

    @Override
    public long getAmountAsLong(int index) {
        return handler.getStackInSlot(index).getCount();
    }

    @Override
    public long getCapacityAsLong(int index, ItemResource resource) {
        return resource.isEmpty() || isValid(index, resource) ? handler.getSlotLimit(index) : 0;
    }

    @Override
    public boolean isValid(int index, ItemResource resource) {
        return !resource.isEmpty() && handler.isItemValid(index, resource.toStack(1));
    }

    @Override
    public int insert(int index, ItemResource resource, int amount, TransactionContext transaction) {
        if (amount <= 0 || resource.isEmpty()) return 0;
        updateSnapshots(transaction);
        ItemStack offered = resource.toStack(amount);
        return amount - handler.insertItem(index, offered, false).getCount();
    }

    @Override
    public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
        if (amount <= 0 || resource.isEmpty() || !resource.matches(handler.getStackInSlot(index))) return 0;
        updateSnapshots(transaction);
        return handler.extractItem(index, amount, false).getCount();
    }

    @Override
    protected NonNullList<ItemStack> createSnapshot() {
        NonNullList<ItemStack> snapshot = NonNullList.withSize(handler.getSlots(), ItemStack.EMPTY);
        for (int i = 0; i < handler.getSlots(); i++) snapshot.set(i, handler.getStackInSlot(i).copy());
        return snapshot;
    }

    @Override
    protected void revertToSnapshot(NonNullList<ItemStack> snapshot) {
        for (int i = 0; i < snapshot.size(); i++) handler.setStackInSlot(i, snapshot.get(i));
    }
}
