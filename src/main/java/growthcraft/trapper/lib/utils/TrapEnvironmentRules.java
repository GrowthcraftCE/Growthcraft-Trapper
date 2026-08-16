package growthcraft.trapper.lib.utils;

public final class TrapEnvironmentRules {

    private TrapEnvironmentRules() {
    }

    public static boolean isAnimalTrapIdeal(
            boolean aboveIsAir,
            boolean northIsValid,
            boolean eastIsValid,
            boolean southIsValid,
            boolean westIsValid
    ) {
        return aboveIsAir && northIsValid && eastIsValid && southIsValid && westIsValid;
    }

    public static boolean isFishtrapIdeal(
            boolean aboveIsLiquid,
            boolean belowIsLiquid,
            boolean northIsLiquid,
            boolean eastIsLiquid,
            boolean southIsLiquid,
            boolean westIsLiquid,
            boolean waterlogged
    ) {
        if (aboveIsLiquid && belowIsLiquid) {
            return true;
        }
        if (northIsLiquid && eastIsLiquid && southIsLiquid && westIsLiquid) {
            return true;
        }
        return waterlogged && ((eastIsLiquid && westIsLiquid) || (northIsLiquid && southIsLiquid));
    }
}
