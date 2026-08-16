package growthcraft.trapper.init;

import growthcraft.trapper.block.entity.AnimalTrapBlockEntity;
import growthcraft.trapper.block.entity.FishtrapBlockEntity;
import growthcraft.trapper.block.entity.SpawnEggTrapBlockEntity;
import growthcraft.trapper.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class GrowthcraftTrapperBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, Reference.MODID
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FishtrapBlockEntity>> FISHTRAP_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.FISHTRAP,
            () -> new BlockEntityType<>(FishtrapBlockEntity::new,
                    GrowthcraftTrapperBlocks.FISHTRAP_BIRCH.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_ACACIA.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_DARK_OAK.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_OAK.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_JUNGLE.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_SPRUCE.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_BAMBOO.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_CHERRY.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_CRIMSON.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_MANGROVE.get(),
                    GrowthcraftTrapperBlocks.FISHTRAP_WARPED.get()

            )
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AnimalTrapBlockEntity>> ANIMAL_TRAP_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.ANIMAL_TRAP,
            () -> new BlockEntityType<>(AnimalTrapBlockEntity::new,
                    GrowthcraftTrapperBlocks.ANIMAL_TRAP_IRON.get(),
                    GrowthcraftTrapperBlocks.ANIMAL_TRAP_COPPER.get(),
                    GrowthcraftTrapperBlocks.ANIMAL_TRAP_GOLD.get(),
                    GrowthcraftTrapperBlocks.ANIMAL_TRAP_DIAMOND.get()
            )
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpawnEggTrapBlockEntity>> SPAWNEGGTRAP_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.SPAWNEGGTRAP,
            () -> new BlockEntityType<>(SpawnEggTrapBlockEntity::new,
                    GrowthcraftTrapperBlocks.SPAWNEGGTRAP.get()
            )
    );

    private GrowthcraftTrapperBlockEntities() {
        /* Disable automative default public constructor */
    }
}
