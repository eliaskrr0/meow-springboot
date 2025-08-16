package meow.common.dto.user.profile.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTargetTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("Ganar 0,5% de peso", TypeTarget.GAIN_WEIGHT_AGGRESSIVELY.getValue());
        assertEquals("Ganar 0,35% de peso", TypeTarget.GAIN_WEIGHT_MODERATELY.getValue());
        assertEquals("Ganar 0,25% de peso", TypeTarget.GAIN_WEIGHT_SLOWLY.getValue());
        assertEquals("Mantener peso", TypeTarget.MAINTAIN_WEIGHT.getValue());
        assertEquals("Perder 0,25% de peso", TypeTarget.LOSE_WEIGHT_SLOWLY.getValue());
        assertEquals("Perder 0,5% de peso", TypeTarget.LOSE_WEIGHT_MODERATELY.getValue());
        assertEquals("Perder 0,75% de peso", TypeTarget.LOSE_WEIGHT_AGGRESSIVELY.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        assertEquals(TypeTarget.GAIN_WEIGHT_AGGRESSIVELY, TypeTarget.fromValue(TypeTarget.GAIN_WEIGHT_AGGRESSIVELY.getValue()));
        assertEquals(TypeTarget.GAIN_WEIGHT_MODERATELY, TypeTarget.fromValue(TypeTarget.GAIN_WEIGHT_MODERATELY.getValue()));
        assertEquals(TypeTarget.GAIN_WEIGHT_SLOWLY, TypeTarget.fromValue(TypeTarget.GAIN_WEIGHT_SLOWLY.getValue()));
        assertEquals(TypeTarget.MAINTAIN_WEIGHT, TypeTarget.fromValue(TypeTarget.MAINTAIN_WEIGHT.getValue()));
        assertEquals(TypeTarget.LOSE_WEIGHT_SLOWLY, TypeTarget.fromValue(TypeTarget.LOSE_WEIGHT_SLOWLY.getValue()));
        assertEquals(TypeTarget.LOSE_WEIGHT_MODERATELY, TypeTarget.fromValue(TypeTarget.LOSE_WEIGHT_MODERATELY.getValue()));
        assertEquals(TypeTarget.LOSE_WEIGHT_AGGRESSIVELY, TypeTarget.fromValue(TypeTarget.LOSE_WEIGHT_AGGRESSIVELY.getValue()));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TypeTarget.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de objetivo incorrecto"));
    }

}