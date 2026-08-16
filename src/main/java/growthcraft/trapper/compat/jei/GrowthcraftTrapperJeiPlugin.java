package growthcraft.trapper.compat.jei;

import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import growthcraft.trapper.shared.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

@JeiPlugin
public class GrowthcraftTrapperJeiPlugin implements IModPlugin {

    public static final ResourceLocation PLUGIN_UID = id("jei_plugin");
    public static final RecipeType<TrapJeiRecipe> FISH_TRAPPING =
            new RecipeType<>(id("fish_trapping"), TrapJeiRecipe.class);
    public static final RecipeType<TrapJeiRecipe> ANIMAL_TRAPPING =
            new RecipeType<>(id("animal_trapping"), TrapJeiRecipe.class);
    public static final RecipeType<TrapJeiRecipe> SPAWN_EGG_TRAPPING =
            new RecipeType<>(id("spawn_egg_trapping"), TrapJeiRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new TrapRecipeCategory(guiHelper, FISH_TRAPPING,
                        "jei.growthcraft_trapper.category.fish_trapping",
                        new ItemStack(GrowthcraftTrapperBlocks.FISHTRAP_OAK.get())),
                new TrapRecipeCategory(guiHelper, ANIMAL_TRAPPING,
                        "jei.growthcraft_trapper.category.animal_trapping",
                        new ItemStack(GrowthcraftTrapperBlocks.ANIMAL_TRAP_IRON.get())),
                new TrapRecipeCategory(guiHelper, SPAWN_EGG_TRAPPING,
                        "jei.growthcraft_trapper.category.spawn_egg_trapping",
                        new ItemStack(GrowthcraftTrapperBlocks.SPAWNEGGTRAP.get()))
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(FISH_TRAPPING, fishRecipes());
        registration.addRecipes(ANIMAL_TRAPPING, animalRecipes());
        registration.addRecipes(SPAWN_EGG_TRAPPING, spawnEggRecipes());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        GrowthcraftTrapperBlocks.BLOCKS.getEntries().forEach(block -> {
            ItemStack stack = new ItemStack(block.get());
            if (block.get() instanceof growthcraft.trapper.block.FishtrapBlock) {
                registration.addRecipeCatalyst(stack, FISH_TRAPPING);
            } else if (block.get() instanceof growthcraft.trapper.block.AnimalTrapBlock) {
                registration.addRecipeCatalyst(stack, ANIMAL_TRAPPING);
            } else if (block.get() instanceof growthcraft.trapper.block.SpawnEggTrapBlock) {
                registration.addRecipeCatalyst(stack, SPAWN_EGG_TRAPPING);
            }
        });
    }

    private static List<TrapJeiRecipe> fishRecipes() {
        return List.of(
                recipe("fish_normal", "jei.growthcraft_trapper.loot.normal",
                        items(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.BREAD),
                        items(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH)),
                recipe("fish_fortune", "jei.growthcraft_trapper.loot.fortune",
                        items(Items.ROTTEN_FLESH, Items.PUFFERFISH, Items.TRIPWIRE_HOOK),
                        items(Items.NAME_TAG, Items.SADDLE, Items.BOW, Items.FISHING_ROD, Items.BOOK, Items.NAUTILUS_SHELL)),
                recipe("fish_junk", "jei.growthcraft_trapper.loot.junk",
                        items(Items.STICK, Items.BOWL, Items.BONE),
                        items(Items.LILY_PAD, Items.LEATHER_BOOTS, Items.LEATHER, Items.BONE, Items.POTION,
                                Items.STRING, Items.FISHING_ROD, Items.BOWL, Items.STICK, Items.INK_SAC,
                                Items.TRIPWIRE_HOOK, Items.ROTTEN_FLESH, Items.BAMBOO))
        );
    }

    private static List<TrapJeiRecipe> animalRecipes() {
        return List.of(
                recipe("animal_wheat", "jei.growthcraft_trapper.loot.cattle", items(Items.WHEAT),
                        items(Items.BEEF, Items.LEATHER)),
                recipe("animal_carrot", "jei.growthcraft_trapper.loot.pig_rabbit", items(Items.CARROT),
                        items(Items.PORKCHOP, Items.RABBIT, Items.RABBIT_HIDE, Items.RABBIT_FOOT)),
                recipe("animal_seeds", "jei.growthcraft_trapper.loot.chicken", items(Items.WHEAT_SEEDS),
                        items(Items.CHICKEN, Items.FEATHER, Items.EGG)),
                recipe("animal_leaves", "jei.growthcraft_trapper.loot.sheep",
                        items(Items.OAK_LEAVES, Items.SPRUCE_LEAVES, Items.BIRCH_LEAVES, Items.JUNGLE_LEAVES,
                                Items.ACACIA_LEAVES, Items.DARK_OAK_LEAVES, Items.MANGROVE_LEAVES, Items.CHERRY_LEAVES),
                        items(Items.MUTTON, Items.WHITE_WOOL, Items.BLACK_WOOL, Items.RED_WOOL, Items.BLUE_WOOL))
        );
    }

    private static List<TrapJeiRecipe> spawnEggRecipes() {
        return List.of(recipe("spawn_egg_wheat", "jei.growthcraft_trapper.loot.spawn_eggs", items(Items.WHEAT),
                items(Items.COW_SPAWN_EGG, Items.SHEEP_SPAWN_EGG, Items.GOAT_SPAWN_EGG)));
    }

    private static TrapJeiRecipe recipe(String path, String descriptionKey, List<ItemStack> inputs,
                                        List<ItemStack> outputs) {
        return new TrapJeiRecipe(id(path), inputs, outputs, descriptionKey);
    }

    private static List<ItemStack> items(Item... items) {
        return java.util.Arrays.stream(items).map(ItemStack::new).toList();
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
