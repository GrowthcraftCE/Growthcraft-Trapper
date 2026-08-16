package growthcraft.trapper.compat.jei;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record TrapJeiRecipe(Identifier id, List<ItemStack> inputs, List<ItemStack> outputs, String descriptionKey) {
}
