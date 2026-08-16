package growthcraft.trapper.init.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GrowthcraftTrapperConfig {

    public static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SERVER;

    public static final String SERVER_CONFIG = "growthcraft-trapper-server.toml";

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_FISHTRAP = "fishtrap";
    private static final String CATEGORY_ANIMALTRAP = "animaltrap";
    private static final String CATEGORY_SPAWNEGGTRAP = "spawneggtrap";

    private static ModConfigSpec.BooleanValue debugEnbabled;


    private static ModConfigSpec.IntValue minTickFishingInMinutes;
    private static ModConfigSpec.IntValue maxTickFishingInMinutes;
    private static ModConfigSpec.IntValue minTickAnimalTrappingInMinutes;
    private static ModConfigSpec.IntValue maxTickAnimalTrappingInMinutes;
    private static ModConfigSpec.IntValue minTickTrappingInMinutes;
    private static ModConfigSpec.IntValue maxTickTrappingInMinutes;

    static {
        initServerConfig(SERVER_BUILDER);
        SERVER = SERVER_BUILDER.build();
    }

    private GrowthcraftTrapperConfig() {
        /* Prevent generation of public constructor */
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
        minTickAnimalTrappingInMinutes = specBuilder
                .comment("Set to the minimum base number of minutes an animal trap should take. Material tiers reduce this time.")
                .defineInRange(String.format("%s.%s", CATEGORY_ANIMALTRAP, "minTickTrappingInMinutes"), 4, 1, 5);
        maxTickAnimalTrappingInMinutes = specBuilder
                .comment("Set to the maximum base number of minutes an animal trap should take. Material tiers reduce this time.")
                .defineInRange(String.format("%s.%s", CATEGORY_ANIMALTRAP, "maxTickTrappingInMinutes"), 12, 5, 60);
        minTickTrappingInMinutes = specBuilder
                .comment("Set to the minimum number of minutes the spawn-egg trap should try to produce loot.")
                .defineInRange(String.format("%s.%s", CATEGORY_SPAWNEGGTRAP, "minTickTrappingInMinutes"), 5, 5, 10);
        maxTickTrappingInMinutes = specBuilder
                .comment("Set to the maximum number of minutes the spawn-egg trap should try to produce loot.")
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

    public static int getMinTickAnimalTrappingInMinutes() {
        return minTickAnimalTrappingInMinutes.get();
    }

    public static int getMaxTickAnimalTrappingInMinutes() {
        return maxTickAnimalTrappingInMinutes.get();
    }

    public static int getMinTickTrappingInMinutes() {
        return minTickTrappingInMinutes.get();
    }

    public static int getMaxTickTrappingInMinutes() {
        return maxTickTrappingInMinutes.get();
    }

}
