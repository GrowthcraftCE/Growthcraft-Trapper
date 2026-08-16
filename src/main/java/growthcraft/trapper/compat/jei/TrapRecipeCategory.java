package growthcraft.trapper.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class TrapRecipeCategory implements IRecipeCategory<TrapJeiRecipe> {

    private static final int WIDTH = 130;
    private static final int HEIGHT = 48;

    private final RecipeType<TrapJeiRecipe> recipeType;
    private final Component title;
    private final IDrawable icon;

    public TrapRecipeCategory(IGuiHelper guiHelper, RecipeType<TrapJeiRecipe> recipeType,
                              String titleKey, ItemStack iconStack) {
        this.recipeType = recipeType;
        this.title = Component.translatable(titleKey);
        this.icon = guiHelper.createDrawableItemStack(iconStack);
    }

    @Override
    public RecipeType<TrapJeiRecipe> getRecipeType() {
        return recipeType;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TrapJeiRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 25, 8)
                .addItemStacks(recipe.inputs());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 88, 8)
                .addItemStacks(recipe.outputs());
    }

    @Override
    public void draw(TrapJeiRecipe recipe, mezz.jei.api.gui.ingredient.IRecipeSlotsView recipeSlotsView,
                     GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
        var font = Minecraft.getInstance().font;
        graphics.text(font, "→", 61, 13, 0xFF555555, false);
        Component description = Component.translatable(recipe.descriptionKey());
        graphics.text(font, description, (WIDTH - font.width(description)) / 2, 32, 0xFF555555, false);
    }

    @Override
    public net.minecraft.resources.Identifier getRegistryName(TrapJeiRecipe recipe) {
        return recipe.id();
    }
}
