package meow.common.dto.user.profile.enums.converter;

import meow.common.dto.user.profile.enums.TypeTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTargetConverterTest {
    private final TypeTargetConverter converter = new TypeTargetConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        assertEquals("Ganar 5kg de peso", converter.convertToDatabaseColumn(TypeTarget.GAIN_FIVE_KILOS_OF_WEIGHT));
        assertEquals("Ganar 2kg de peso", converter.convertToDatabaseColumn(TypeTarget.GAIN_TWO_KILOS_OF_WEIGHT));
        assertEquals("Mantener peso", converter.convertToDatabaseColumn(TypeTarget.MAINTAIN_WEIGHT));
        assertEquals("Perder 2kg de peso", converter.convertToDatabaseColumn(TypeTarget.LOSE_TWO_KILOS_OF_WEIGHT));
        assertEquals("Perder 5kg de peso", converter.convertToDatabaseColumn(TypeTarget.LOSE_FIVE_KILOS_OF_WEIGHT));
    }

    @Test
    void shouldConvertToEntityAttribute() {
        assertEquals(TypeTarget.GAIN_FIVE_KILOS_OF_WEIGHT, converter.convertToEntityAttribute("Ganar 5kg de peso"));
        assertEquals(TypeTarget.GAIN_TWO_KILOS_OF_WEIGHT, converter.convertToEntityAttribute("Ganar 2kg de peso"));
        assertEquals(TypeTarget.MAINTAIN_WEIGHT, converter.convertToEntityAttribute("Mantener peso"));
        assertEquals(TypeTarget.LOSE_TWO_KILOS_OF_WEIGHT, converter.convertToEntityAttribute("Perder 2kg de peso"));
        assertEquals(TypeTarget.LOSE_FIVE_KILOS_OF_WEIGHT, converter.convertToEntityAttribute("Perder 5kg de peso"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de objetivo incorrecto"));
    }

}