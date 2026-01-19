package growthcraft.trapper.init;

import growthcraft.trapper.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

public class GrowthcraftTrapperItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MODID);

    public static void registerBlockItems() {

    }

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeBlocks.contains(registryName.toString());
    }

}
