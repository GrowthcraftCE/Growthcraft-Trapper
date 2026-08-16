package growthcraft.trapper.block.entity;

import growthcraft.trapper.GrowthcraftTrapper;
import growthcraft.trapper.block.AnimalTrapBlock;
import growthcraft.trapper.init.GrowthcraftTrapperBlockEntities;
import growthcraft.trapper.init.config.GrowthcraftTrapperConfig;
import growthcraft.trapper.lib.handler.WrappedInventoryHandler;
import growthcraft.trapper.lib.handler.LegacyItemResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import growthcraft.trapper.lib.utils.BlockStateUtils;
import growthcraft.trapper.lib.utils.TickUtils;
import growthcraft.trapper.lib.utils.TrapBaitTypes;
import growthcraft.trapper.lib.utils.TrapEnvironmentRules;
import growthcraft.trapper.screen.AnimalTrapMenu;
import growthcraft.trapper.shared.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class AnimalTrapBlockEntity extends BlockEntity implements BlockEntityTicker<AnimalTrapBlockEntity>, MenuProvider {

    private final int minTick = TickUtils.toTicks(GrowthcraftTrapperConfig.getMinTickAnimalTrappingInMinutes(), "minutes");
    private final int maxTick = TickUtils.toTicks(GrowthcraftTrapperConfig.getMaxTickAnimalTrappingInMinutes(), "minutes");
    private int tickTimer = 0;
    private int tickCooldown = 0;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> true;
                case 1 -> false;
                case 2 -> false;
                case 3 -> false;
                case 4 -> false;
                case 5 -> false;
                case 6 -> false;
                default -> false;
            };
        }
    };

    private final Map<Direction, WrappedInventoryHandler> directionWrappedHandlerMap =
            Map.of(
                    Direction.UP, new WrappedInventoryHandler(
                            itemStackHandler,
                            (index) -> index == 0,
                            (index, stack) -> itemStackHandler.isItemValid(0, stack)
                    ),
                    Direction.DOWN, new WrappedInventoryHandler(
                            itemStackHandler,
                            (i) -> i >= 1,
                            (i, s) -> false
                    ),
                    Direction.NORTH, new WrappedInventoryHandler(
                            itemStackHandler,
                            (index) -> index == 0,
                            (index, stack) -> itemStackHandler.isItemValid(0, stack)
                    ),
                    Direction.SOUTH, new WrappedInventoryHandler(
                            itemStackHandler,
                            (index) -> index == 0,
                            (index, stack) -> itemStackHandler.isItemValid(0, stack)
                    ),
                    Direction.EAST, new WrappedInventoryHandler(
                            itemStackHandler,
                            (index) -> index == 0,
                            (index, stack) -> itemStackHandler.isItemValid(0, stack)
                    ),
                    Direction.WEST, new WrappedInventoryHandler(
                            itemStackHandler,
                            (index) -> index == 0,
                            (index, stack) -> itemStackHandler.isItemValid(0, stack)
                    )
            );

    public IItemHandler getItemHandler(@Nullable Direction side) {
        if (side == null) return itemStackHandler;
        return directionWrappedHandlerMap.get(side);
    }

    public ResourceHandler<ItemResource> getResourceHandler(@Nullable Direction side) {
        return new LegacyItemResourceHandler((net.neoforged.neoforge.items.IItemHandlerModifiable) getItemHandler(side));
    }

    public AnimalTrapBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GrowthcraftTrapperBlockEntities.ANIMAL_TRAP_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, AnimalTrapBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        tickTimer++;

        if (tickCooldown != 0 && tickTimer > tickCooldown && canDoTrapping(level, blockPos)) {
            this.doTrapping(blockPos);
            tickTimer = 0;
            tickCooldown = TickUtils.applyProcessingFactor(
                    TickUtils.getRandomTickCooldown(minTick, maxTick),
                    ((AnimalTrapBlock) level.getBlockState(blockPos).getBlock()).getProcessingFactor()
            );
        } else if(tickCooldown == 0 && canDoTrapping(level,blockPos)) {
            tickCooldown = TickUtils.applyProcessingFactor(
                    TickUtils.getRandomTickCooldown(minTick, maxTick),
                    ((AnimalTrapBlock) level.getBlockState(blockPos).getBlock()).getProcessingFactor()
            );
        }
    }

    private boolean canDoTrapping(Level level, BlockPos blockPos) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(level, blockPos);

        return TrapEnvironmentRules.isAnimalTrapIdeal(
                blockMap.get("up") == Blocks.AIR,
                isValidHorizontalBlock(blockMap.get("north")),
                isValidHorizontalBlock(blockMap.get("east")),
                isValidHorizontalBlock(blockMap.get("south")),
                isValidHorizontalBlock(blockMap.get("west"))
        );
    }

    private static boolean isValidHorizontalBlock(Block block) {
        return block != Blocks.AIR && !(block instanceof LiquidBlock);
    }

    public boolean hasIdealConditions() {
        return level != null && canDoTrapping(level, worldPosition);
    }

    public String getConditionSubjectKey() {
        return switch (TrapBaitTypes.animal(itemStackHandler.getStackInSlot(0))) {
            case WHEAT -> "message.growthcraft_trapper.conditions.subject.cattle";
            case CARROT -> "message.growthcraft_trapper.conditions.subject.pig_rabbit";
            case SEEDS -> "message.growthcraft_trapper.conditions.subject.chicken";
            case LEAVES -> "message.growthcraft_trapper.conditions.subject.sheep";
            case INVALID -> "message.growthcraft_trapper.conditions.subject.invalid_bait";
        };
    }

    private void doTrapping(@NotNull BlockPos blockPos) {
        if (level == null) return;

        ItemStack baitItemStack = itemStackHandler.getStackInSlot(0);

        LootTable lootTable;

        String lootTableType = "";

        // Depending on the bait that was used, determines what gets caught.
        if (TrapBaitTypes.animal(baitItemStack) == TrapBaitTypes.Animal.WHEAT) {
            lootTableType = "wheat";
            lootTable = level.getServer().reloadableRegistries().getLootTable(Reference.LootTables.ANIMAL_TRAP_WHEAT);
        } else if (TrapBaitTypes.animal(baitItemStack) == TrapBaitTypes.Animal.CARROT) {
            lootTableType = "carrot";
            lootTable = level.getServer().reloadableRegistries().getLootTable(Reference.LootTables.ANIMAL_TRAP_CARROT);
        } else if (TrapBaitTypes.animal(baitItemStack) == TrapBaitTypes.Animal.SEEDS) {
            lootTableType = "seeds_wheat";
            lootTable = level.getServer().reloadableRegistries().getLootTable(Reference.LootTables.ANIMAL_TRAP_SEEDS);
        } else if (TrapBaitTypes.animal(baitItemStack) == TrapBaitTypes.Animal.LEAVES) {
            lootTableType = "leaves";
            lootTable = level.getServer().reloadableRegistries().getLootTable(Reference.LootTables.ANIMAL_TRAP_LEAVES);
        } else {
            lootTableType = "invalid_bait";
            return;
        }

        if (GrowthcraftTrapperConfig.isDebugEnabled()) {
            GrowthcraftTrapper.LOGGER.debug(
                    "Animal trap at {} used bait {} with loot table {}.",
                    blockPos.toShortString(), baitItemStack, lootTableType
            );
        }

        // If loot table is null, fail now.
        if (lootTable == null) return;

        LootParams lootContext = new LootParams.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()))
                .create(LootContextParamSets.EMPTY);

        List<ItemStack> lootItemStacks = lootTable.getRandomItems(lootContext);

        for (ItemStack itemStack : lootItemStacks) {
            if (GrowthcraftTrapperConfig.isDebugEnabled()) {
                GrowthcraftTrapper.LOGGER.debug(
                        "Animal trap at {} caught {} from the {} loot table.",
                        blockPos.toShortString(), itemStack, lootTableType
                );
            }

            if (itemStack.getItem() != Items.AIR) {
                for (int i = 1; i < itemStackHandler.getSlots(); i++) {
                    ItemStack storedItemStack = itemStackHandler.getStackInSlot(i);
                    if (itemStackHandler.getStackInSlot(i).isEmpty() || storedItemStack.getItem() == itemStack.getItem()) {
                        itemStackHandler.setStackInSlot(i, new ItemStack(itemStack.getItem(), itemStackHandler.getStackInSlot(i).getCount() + 1));
                        break;
                    }
                }
            }
        }

        itemStackHandler.getStackInSlot(0).shrink(1);

        this.getLevel().playSound(null, this.worldPosition, SoundEvents.TRIPWIRE_CLICK_ON, SoundSource.BLOCKS, 0.5F, 0.5F);

    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        itemStackHandler.deserialize(input.childOrEmpty("inventory"));
        this.tickTimer = input.getIntOr("tickTimer", 0);
        this.tickCooldown = input.getIntOr("tickCooldown", 0);
        this.customName = input.read("CustomName", ComponentSerialization.CODEC).orElse(null);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        itemStackHandler.serialize(output.child("inventory"));
        output.putInt("tickTimer", this.tickTimer);
        output.putInt("tickCooldown", this.tickCooldown);
        output.storeNullable("CustomName", ComponentSerialization.CODEC, this.customName);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.growthcraft_trapper.animal_trap");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new AnimalTrapMenu(containerId, inventory, this);
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.getLevel(), this.worldPosition, inventory);
    }
}
