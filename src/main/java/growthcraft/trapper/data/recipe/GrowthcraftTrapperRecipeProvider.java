package growthcraft.trapper.data.recipe;

import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import growthcraft.trapper.shared.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class GrowthcraftTrapperRecipeProvider extends RecipeProvider {

    public GrowthcraftTrapperRecipeProvider(PackOutput output,
                                             CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_ACACIA.get(), Items.ACACIA_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_BAMBOO.get(), Items.BAMBOO_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_BIRCH.get(), Items.BIRCH_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_CHERRY.get(), Items.CHERRY_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_CRIMSON.get(), Items.CRIMSON_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_DARK_OAK.get(), Items.DARK_OAK_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_JUNGLE.get(), Items.JUNGLE_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_MANGROVE.get(), Items.MANGROVE_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_OAK.get(), Items.OAK_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_SPRUCE.get(), Items.SPRUCE_PLANKS);
        addFishtrap(output, GrowthcraftTrapperBlocks.FISHTRAP_WARPED.get(), Items.WARPED_PLANKS);

        addAnimalTrap(output, GrowthcraftTrapperBlocks.ANIMAL_TRAP_COPPER.get(), Items.COPPER_INGOT);
        addAnimalTrap(output, GrowthcraftTrapperBlocks.ANIMAL_TRAP_DIAMOND.get(), Items.DIAMOND);
        addAnimalTrap(output, GrowthcraftTrapperBlocks.ANIMAL_TRAP_GOLD.get(), Items.GOLD_INGOT);
        addAnimalTrap(output, GrowthcraftTrapperBlocks.ANIMAL_TRAP_IRON.get(), Items.IRON_INGOT);

        addSpawnEggTrap(output);
    }

    private static void addFishtrap(RecipeOutput output, ItemLike result, Item material) {
        addTrap(output, result, material, Items.LEAD, Items.STRING, "fishtrap");
    }

    private static void addAnimalTrap(RecipeOutput output, ItemLike result, Item material) {
        addTrap(output, result, material, Items.BUCKET, Items.IRON_BARS, "animal_trap");
    }

    private static void addSpawnEggTrap(RecipeOutput output) {
        addTrap(output, GrowthcraftTrapperBlocks.SPAWNEGGTRAP.get(), Items.NETHERITE_INGOT,
                Items.BUCKET, Items.IRON_BARS, "spawn_egg_trap");
    }

    private static void addTrap(RecipeOutput output, ItemLike result, Item material,
                                Item center, Item corners, String group) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("ACA")
                .pattern("CBC")
                .pattern("ACA")
                .define('A', material)
                .define('B', center)
                .define('C', corners)
                .group(group)
                .unlockedBy(getHasName(material), has(material))
                .save(output, ResourceLocation.fromNamespaceAndPath(
                        Reference.MODID,
                        net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(result.asItem()).getPath()));
    }
}
