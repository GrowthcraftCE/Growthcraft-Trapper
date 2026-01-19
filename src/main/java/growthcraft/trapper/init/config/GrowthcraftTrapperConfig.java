package growthcraft.trapper.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftTrapperConfig {

    public static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SERVER;

    public static final String SERVER_CONFIG = "growthcraft-trapper-server.toml";

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_FISHTRAP = "fishtrap";
    private static final String CATEGORY_SPAWNEGGTRAP = "spawneggtrap";

    private static ModConfigSpec.BooleanValue debugEnbabled;


    private static ModConfigSpec.IntValue minTickFishingInMinutes;
    private static ModConfigSpec.IntValue maxTickFishingInMinutes;
    private static ModConfigSpec.IntValue minTickTrappingInMinutes;
    private static ModConfigSpec.IntValue maxTickTrappingInMinutes;

    static {
        initServerConfig(SERVER_BUILDER);
        SERVER = SERVER_BUILDER.build();
    }

    private GrowthcraftTrapperConfig() {
        /* Prevent generation of public constructor */
    }

    public static void loadConfig() {
        loadConfig(SERVER, FMLPaths.CONFIGDIR.get().resolve(SERVER_CONFIG).toString());
    }

    public static void loadConfig(ModConfigSpec configSpec, String path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(
                new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

        fileConfig.load();
        configSpec.setConfig(fileConfig);
    }

    public static void initServerConfig(ModConfigSpec.Builder specBuilder) {
        debugEnbabled = specBuilder
                .comment("Set to true to enable debug logging.")
                .define(String.format("%s.%s", CATEGORY_GENERAL, "enableDebugLogging"), false);
        minTickFishingInMinutes = specBuilder
                .comment("Set to the minimum number of minutes the fishtrap should try to fish.")
                .defineInRange(String.format("%s.%s", CATEGORY_FISHTRAP, "minTickFishingInMinutes"), 1, 1, 5);
        maxTickFishingInMinutes = specBuilder
                .comment("Set to the maximum number of minutes the fishtrap should try to fish.")
                .defineInRange(String.format("%s.%s", CATEGORY_FISHTRAP, "maxTickFishingInMinutes"), 5, 5, 60);
        minTickTrappingInMinutes = specBuilder
                .comment("Set to the minimum number of minutes the animal trap should try to trap animals.")
                .defineInRange(String.format("%s.%s", CATEGORY_SPAWNEGGTRAP, "minTickTrappingInMinutes"), 5, 5, 10);
        maxTickTrappingInMinutes = specBuilder
                .comment("Set to the maximum number of minutes the animal trap should try to trap.")
                .defineInRange(String.format("%s.%s", CATEGORY_SPAWNEGGTRAP, "maxTickTrappingInMinutes"), 10, 10, 60);
    }

    public static boolean isDebugEnabled() {
        return debugEnbabled.get();
    }

    public static int getMinTickFishingInMinutes() {
        return minTickFishingInMinutes.get();
    }

    public static int getMaxTickFishingInMinutes() {
        return maxTickFishingInMinutes.get();
    }

    public static int getMinTickTrappingInMinutes() {
        return minTickTrappingInMinutes.get();
    }

    public static int getMaxTickTrappingInMinutes() {
        return maxTickTrappingInMinutes.get();
    }

}
