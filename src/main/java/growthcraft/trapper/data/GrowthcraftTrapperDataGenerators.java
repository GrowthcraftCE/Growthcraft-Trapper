package growthcraft.trapper.data;

import growthcraft.trapper.data.recipe.GrowthcraftTrapperRecipeProvider;
import growthcraft.trapper.shared.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Reference.MODID)
public final class GrowthcraftTrapperDataGenerators {

    private GrowthcraftTrapperDataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeServer(),
                new GrowthcraftTrapperRecipeProvider(output, event.getLookupProvider()));
    }
}
