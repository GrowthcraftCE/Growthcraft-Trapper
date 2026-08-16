package growthcraft.trapper.compat.jei;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record TrapJeiRecipe(ResourceLocation id, List<ItemStack> inputs, List<ItemStack> outputs, String descriptionKey) {
}
