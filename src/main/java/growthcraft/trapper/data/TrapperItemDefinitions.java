package growthcraft.trapper.data;

import com.google.gson.JsonObject;
import growthcraft.trapper.shared.Reference;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/** Generates Minecraft 26 item definitions that point at the existing item models. */
public final class TrapperItemDefinitions implements DataProvider {
    private static final String[] BLOCK_ITEMS = {
            "animal_trap_copper", "animal_trap_diamond", "animal_trap_gold", "animal_trap_iron",
            "fishtrap_acacia", "fishtrap_bamboo", "fishtrap_birch", "fishtrap_cherry",
            "fishtrap_crimson", "fishtrap_dark_oak", "fishtrap_jungle", "fishtrap_mangrove",
            "fishtrap_oak", "fishtrap_spruce", "fishtrap_warped", "spawneggtrap"
    };

    private final PackOutput.PathProvider paths;

    public TrapperItemDefinitions(PackOutput output) {
        this.paths = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.allOf(Arrays.stream(BLOCK_ITEMS).map(name -> {
            JsonObject model = new JsonObject();
            model.addProperty("type", "minecraft:model");
            model.addProperty("model", Reference.MODID + ":item/" + name);
            JsonObject definition = new JsonObject();
            definition.add("model", model);
            return DataProvider.saveStable(output, definition,
                    paths.json(Identifier.fromNamespaceAndPath(Reference.MODID, name)));
        }).toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Growthcraft Trapper Item Definitions";
    }
}
