package growthcraft.trapper.shared;

import net.minecraft.resources.ResourceLocation;

public class Reference {
    public static final String MODID = "growthcraft_trapper";

    public static final String NAME = "Growthcraft Trapper";
    public static final String NAME_SHORT = "trapper";
    public static final String VERSION = "8.1.0";

    private Reference() { /* Prevent default public constructor */ }

    public static class UnlocalizedName {
        public static final String FISHTRAP = "fishtrap";

        public static final String FISHTRAP_CONTAINER = "fishtrap_container";
        public static final String FISHTRAP_OAK = "fishtrap_oak";
        public static final String FISHTRAP_ACACIA = "fishtrap_acacia";
        public static final String FISHTRAP_SPRUCE = "fishtrap_spruce";
        public static final String FISHTRAP_JUNGLE = "fishtrap_jungle";
        public static final String FISHTRAP_DARK_OAK = "fishtrap_dark_oak";
        public static final String FISHTRAP_BIRCH = "fishtrap_birch";
        public static final String SPAWNEGGTRAP = "spawneggtrap";
        public static final String TAG_FISHING_BAIT = "fishing_bait";

        public static final String SOUND_FISHTRAP_OPEN = "fishtrap_open";
        public static final String CREATIVE_TAB = "tab";

        private UnlocalizedName() { /* Disable default public constructor. */ }

    }

    public static class LootTables {
        public static final ResourceLocation SPAWNEGGTRAP_WHEAT = new ResourceLocation(Reference.MODID, "spawneggtrap_wheat");
    }

}
