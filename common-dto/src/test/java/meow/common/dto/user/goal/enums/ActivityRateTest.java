package meow.common.dto.user.goal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRateTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("Sedentario", ActivityRate.SEDENTARY.getValue());
        assertEquals("Ligera", ActivityRate.LIGHT.getValue());
        assertEquals("Moderado", ActivityRate.MODERATE.getValue());
        assertEquals("Alto", ActivityRate.HEAVY.getValue());
        assertEquals("Atleta", ActivityRate.VERY_HEAVY.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        for (ActivityRate ar : ActivityRate.values()) {
            assertEquals(ar, ActivityRate.fromValue(ar.getValue()));
            assertEquals(ar, ActivityRate.fromValue(ar.name()));
        }
    }

    @Test
    void shouldCreateFromFactor() {
        assertEquals(ActivityRate.SEDENTARY, ActivityRate.fromValue("1.20"));
        assertEquals(ActivityRate.LIGHT, ActivityRate.fromValue("1.375"));
        assertEquals(ActivityRate.MODERATE, ActivityRate.fromValue("1.55"));
        assertEquals(ActivityRate.HEAVY, ActivityRate.fromValue("1.725"));
        assertEquals(ActivityRate.VERY_HEAVY, ActivityRate.fromValue("1.90"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ActivityRate.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Valor de actividad incorrecto"));
    }
}
