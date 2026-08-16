package growthcraft.trapper.lib.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrapEnvironmentRulesTest {

    @Test
    void animalTrapRequiresAirAboveAndAllFourValidSides() {
        assertTrue(TrapEnvironmentRules.isAnimalTrapIdeal(true, true, true, true, true));
        assertFalse(TrapEnvironmentRules.isAnimalTrapIdeal(false, true, true, true, true));
        assertFalse(TrapEnvironmentRules.isAnimalTrapIdeal(true, false, true, true, true));
        assertFalse(TrapEnvironmentRules.isAnimalTrapIdeal(true, true, false, true, true));
        assertFalse(TrapEnvironmentRules.isAnimalTrapIdeal(true, true, true, false, true));
        assertFalse(TrapEnvironmentRules.isAnimalTrapIdeal(true, true, true, true, false));
    }

    @Test
    void fishtrapAcceptsVerticalLiquid() {
        assertTrue(TrapEnvironmentRules.isFishtrapIdeal(true, true, false, false, false, false, false));
    }

    @Test
    void fishtrapAcceptsFourHorizontalLiquidBlocks() {
        assertTrue(TrapEnvironmentRules.isFishtrapIdeal(false, false, true, true, true, true, false));
    }

    @Test
    void waterloggedFishtrapAcceptsOppositeHorizontalLiquidBlocks() {
        assertTrue(TrapEnvironmentRules.isFishtrapIdeal(false, false, false, true, false, true, true));
        assertTrue(TrapEnvironmentRules.isFishtrapIdeal(false, false, true, false, true, false, true));
    }

    @Test
    void fishtrapRejectsIncompleteConditions() {
        assertFalse(TrapEnvironmentRules.isFishtrapIdeal(false, false, false, true, false, true, false));
        assertFalse(TrapEnvironmentRules.isFishtrapIdeal(false, false, true, true, false, false, true));
    }
}
