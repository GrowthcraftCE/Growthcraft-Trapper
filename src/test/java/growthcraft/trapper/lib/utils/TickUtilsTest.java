package growthcraft.trapper.lib.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TickUtilsTest {

    @Test
    void convertsSupportedTimeUnitsToTicks() {
        assertEquals(20, TickUtils.fromSeconds(1));
        assertEquals(1_200, TickUtils.fromMinutes(1));
        assertEquals(72_000, TickUtils.fromHours(1));
        assertEquals(3_600, TickUtils.toTicks(3, "minutes"));
    }

    @Test
    void randomCooldownStaysWithinHalfOpenRange() {
        for (int i = 0; i < 1_000; i++) {
            int cooldown = TickUtils.getRandomTickCooldown(100, 200);
            assertTrue(cooldown >= 100 && cooldown < 200);
        }
    }

    @Test
    void equalCooldownBoundsProduceFixedCooldown() {
        assertEquals(100, TickUtils.getRandomTickCooldown(100, 100));
    }

    @Test
    void rejectsReversedCooldownBounds() {
        assertThrows(IllegalArgumentException.class, () -> TickUtils.getRandomTickCooldown(200, 100));
    }

    @Test
    void appliesMaterialProcessingFactor() {
        assertEquals(12_000, TickUtils.applyProcessingFactor(12_000, 1));
        assertEquals(6_000, TickUtils.applyProcessingFactor(12_000, 2));
        assertEquals(4_000, TickUtils.applyProcessingFactor(12_000, 3));
        assertEquals(3_000, TickUtils.applyProcessingFactor(12_000, 4));
        assertThrows(IllegalArgumentException.class, () -> TickUtils.applyProcessingFactor(12_000, 0));
    }
}
