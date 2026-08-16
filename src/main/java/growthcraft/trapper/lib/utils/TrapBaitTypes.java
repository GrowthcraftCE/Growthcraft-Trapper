package growthcraft.trapper.lib.utils;

import growthcraft.trapper.init.GrowthcraftTrapperTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public final class TrapBaitTypes {

    private TrapBaitTypes() {
    }

    public enum Fish {
        NORMAL,
        FORTUNE,
        JUNK
    }

    public enum Animal {
        WHEAT,
        CARROT,
        SEEDS,
        LEAVES,
        INVALID
    }

    public enum SpawnEgg {
        WHEAT,
        INVALID
    }

    public static Fish fish(ItemStack bait) {
        if (bait.is(GrowthcraftTrapperTags.Items.FISHTRAP_BAIT_FORTUNE)) {
            return Fish.FORTUNE;
        }
        if (bait.is(GrowthcraftTrapperTags.Items.FISHTRAP_BAIT)) {
            return Fish.NORMAL;
        }
        return Fish.JUNK;
    }

    public static Animal animal(ItemStack bait) {
        if (bait.is(Tags.Items.CROPS_WHEAT)) {
            return Animal.WHEAT;
        }
        if (bait.is(Tags.Items.CROPS_CARROT)) {
            return Animal.CARROT;
        }
        if (bait.is(Tags.Items.SEEDS_WHEAT)) {
            return Animal.SEEDS;
        }
        if (bait.is(ItemTags.LEAVES)) {
            return Animal.LEAVES;
        }
        return Animal.INVALID;
    }

    public static SpawnEgg spawnEgg(ItemStack bait) {
        return bait.is(Items.WHEAT) ? SpawnEgg.WHEAT : SpawnEgg.INVALID;
    }
}
