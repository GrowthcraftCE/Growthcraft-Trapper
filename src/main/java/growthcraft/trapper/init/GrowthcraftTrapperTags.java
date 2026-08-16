package growthcraft.trapper.init;

import growthcraft.trapper.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GrowthcraftTrapperTags {

    public static class Items {

        public static final TagKey<Item> FISHTRAP_BAIT = getTag("fishtrap_bait");
        public static final TagKey<Item> FISHTRAP_BAIT_FORTUNE = getTag("fishtrap_bait_fortune");

        private static TagKey<Item> getTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Reference.MODID, name));
        }

    }
}
