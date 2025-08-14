package meow.common.dto.user.profile.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTargetTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("Ganar 5kg de peso", TypeTarget.GAIN_FIVE_KILOS_OF_WEIGHT.getValue());
        assertEquals("Ganar 2kg de peso", TypeTarget.GAIN_TWO_KILOS_OF_WEIGHT.getValue());
        assertEquals("Mantener peso", TypeTarget.MAINTAIN_WEIGHT.getValue());
        assertEquals("Perder 2kg de peso", TypeTarget.LOSE_TWO_KILOS_OF_WEIGHT.getValue());
        assertEquals("Perder 5kg de peso", TypeTarget.LOSE_FIVE_KILOS_OF_WEIGHT.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        assertEquals(TypeTarget.GAIN_FIVE_KILOS_OF_WEIGHT, TypeTarget.fromValue(String.valueOf(TypeTarget.GAIN_FIVE_KILOS_OF_WEIGHT)));
        assertEquals(TypeTarget.GAIN_TWO_KILOS_OF_WEIGHT, TypeTarget.fromValue(String.valueOf(TypeTarget.GAIN_TWO_KILOS_OF_WEIGHT)));
        assertEquals(TypeTarget.MAINTAIN_WEIGHT, TypeTarget.fromValue(String.valueOf(TypeTarget.MAINTAIN_WEIGHT)));
        assertEquals(TypeTarget.LOSE_TWO_KILOS_OF_WEIGHT, TypeTarget.fromValue(String.valueOf(TypeTarget.LOSE_TWO_KILOS_OF_WEIGHT)));
        assertEquals(TypeTarget.LOSE_FIVE_KILOS_OF_WEIGHT, TypeTarget.fromValue(String.valueOf(TypeTarget.LOSE_FIVE_KILOS_OF_WEIGHT)));

    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TypeTarget.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de objetivo incorrecto"));
    }

}