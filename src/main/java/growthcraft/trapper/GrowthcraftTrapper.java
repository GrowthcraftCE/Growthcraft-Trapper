package growthcraft.trapper;

import growthcraft.trapper.block.entity.AnimalTrapBlockEntity;
import growthcraft.trapper.block.entity.FishtrapBlockEntity;
import growthcraft.trapper.block.entity.SpawnEggTrapBlockEntity;
import growthcraft.trapper.init.*;
import growthcraft.trapper.init.client.GrowthcraftTrapperBlockRenders;
import growthcraft.trapper.init.config.GrowthcraftTrapperConfig;
import growthcraft.trapper.shared.Reference;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.neoforged.neoforge.common.NeoForge.EVENT_BUS;

@Mod(Reference.MODID)
public class GrowthcraftTrapper {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftTrapper(IEventBus modEventBus) {
        modEventBus.addListener(this::setup);
        EVENT_BUS.addListener(this::onServerStarting);
        modEventBus.addListener(this::clientSetupEvent);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::registerScreens);

        GrowthcraftTrapperConfig.loadConfig();

        GrowthcraftTrapperBlocks.BLOCKS.register(modEventBus);
        GrowthcraftTrapperItems.ITEMS.register(modEventBus);
        GrowthcraftTrapperBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GrowthcraftTrapperMenus.MENUS.register(modEventBus);

        GrowthcraftTrapperCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        GrowthcraftTrapperMenus.registerMenus(event);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                GrowthcraftTrapperBlockEntities.ANIMAL_TRAP_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler(side)
        );
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                GrowthcraftTrapperBlockEntities.FISHTRAP_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler(side)
        );
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                GrowthcraftTrapperBlockEntities.SPAWNEGGTRAP_BLOCK_ENTITY.get(),
                (be, side) -> be.getItemHandler(side)
        );
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do nothing for now ...
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftTrapperBlockRenders.registerBlockRenders();
    }

    @net.neoforged.bus.api.SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info(String.format("%s is starting ...", Reference.NAME));
    }

}
