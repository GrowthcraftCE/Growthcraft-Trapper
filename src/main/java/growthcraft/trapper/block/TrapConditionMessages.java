package growthcraft.trapper.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

final class TrapConditionMessages {

    private TrapConditionMessages() {
    }

    static void show(Player player, String subjectKey, boolean ideal) {
        String resultKey = ideal
                ? "message.growthcraft_trapper.conditions.ideal"
                : "message.growthcraft_trapper.conditions.not_ideal";
        ChatFormatting color = ideal ? ChatFormatting.GREEN : ChatFormatting.YELLOW;
        player.sendOverlayMessage(Component.translatable(resultKey, Component.translatable(subjectKey)).withStyle(color));
    }
}
