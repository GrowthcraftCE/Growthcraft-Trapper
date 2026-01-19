package growthcraft.trapper.init;

import growthcraft.trapper.shared.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;

public class GrowthcraftTrapperCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register(Reference.UnlocalizedName.CREATIVE_TAB, () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group." + Reference.MODID + ".tab"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> GrowthcraftTrapperBlocks.FISHTRAP_OAK.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                final Set<Item> seen = new HashSet<>();

                // Add blocks
                GrowthcraftTrapperBlocks.BLOCKS.getEntries().forEach(
                        blockRegistryObject -> {
                            if (!GrowthcraftTrapperBlocks.excludeBlockItemRegistry(blockRegistryObject.getId())) {
                                ItemStack stack = new ItemStack(blockRegistryObject.get());
                                if (seen.add(stack.getItem())) output.accept(stack);
                            }
                        }
                );
                // Add items
                GrowthcraftTrapperItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                    if (!GrowthcraftTrapperItems.excludeItemRegistry(itemRegistryObject.getId())) {
                        ItemStack stack = new ItemStack(itemRegistryObject.get());
                        if (seen.add(stack.getItem())) output.accept(stack);
                    }
                });
            }).build());
}
