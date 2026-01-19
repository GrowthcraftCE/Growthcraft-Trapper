package growthcraft.trapper.init;

import growthcraft.trapper.screen.*;
import growthcraft.trapper.shared.Reference;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class GrowthcraftTrapperMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
            Registries.MENU, Reference.MODID
    );

    public static final DeferredHolder<MenuType<?>, MenuType<AnimalTrapMenu>> ANIMAL_TRAP_MENU =
            registerMenuType(Reference.UnlocalizedName.ANIMAL_TRAP_CONTAINER, AnimalTrapMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<FishtrapMenu>> FISHTRAP_MENU =
            registerMenuType(Reference.UnlocalizedName.FISHTRAP, FishtrapMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<SpawnEggTrapMenu>> SPAWNEGGTRAP_MENU =
            registerMenuType(Reference.UnlocalizedName.SPAWNEGGTRAP, SpawnEggTrapMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void registerMenus() {
        MenuScreens.register(ANIMAL_TRAP_MENU.get(), AnimalTrapScreen::new);
        MenuScreens.register(FISHTRAP_MENU.get(), FishtrapScreen::new);
        MenuScreens.register(SPAWNEGGTRAP_MENU.get(), SpawnEggTrapScreen::new);
    }

}
