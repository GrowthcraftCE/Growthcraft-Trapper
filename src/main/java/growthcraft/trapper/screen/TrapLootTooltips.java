package growthcraft.trapper.screen;

import growthcraft.trapper.lib.utils.TrapBaitTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

final class TrapLootTooltips {

    private TrapLootTooltips() {
    }

    static List<Component> fish(ItemStack bait) {
        return tooltip("fish." + TrapBaitTypes.fish(bait).name().toLowerCase());
    }

    static List<Component> animal(ItemStack bait) {
        return tooltip("animal." + TrapBaitTypes.animal(bait).name().toLowerCase());
    }

    static List<Component> spawnEgg(ItemStack bait) {
        return tooltip("spawn_egg." + TrapBaitTypes.spawnEgg(bait).name().toLowerCase());
    }

    private static List<Component> tooltip(String type) {
        String key = "tooltip.growthcraft_trapper.loot_filter." + type;
        return List.of(
                Component.translatable(key).withStyle(ChatFormatting.GOLD),
                Component.translatable(key + ".drops").withStyle(ChatFormatting.GRAY)
        );
    }
}
