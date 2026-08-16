package growthcraft.trapper.screen;

import growthcraft.trapper.shared.Reference;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class AnimalTrapScreen extends AbstractContainerScreen<AnimalTrapMenu> {

    private static final int FILTER_ICON_X = 7;
    private static final int FILTER_ICON_Y = 24;
    private static final int FILTER_ICON_SIZE = 9;

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Reference.MODID,
            "textures/gui/fishtrap_screen.png");

    public AnimalTrapScreen(AnimalTrapMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0,
                imageWidth, imageHeight, 256, 256);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        if (isHovering(FILTER_ICON_X, FILTER_ICON_Y, FILTER_ICON_SIZE, FILTER_ICON_SIZE, mouseX, mouseY)) {
            graphics.setTooltipForNextFrame(font, TrapLootTooltips.animal(menu.getBaitStack()),
                    java.util.Optional.empty(), mouseX, mouseY);
            return;
        }
        super.extractTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        guiGraphics.text(this.font, this.title,
                this.titleLabelX, this.titleLabelY, 4210752, false);
        guiGraphics.text(this.font, this.playerInventoryTitle,
                this.inventoryLabelX, this.inventoryLabelY - 32, 4210752, false);
    }
}
