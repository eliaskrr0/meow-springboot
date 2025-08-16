package meow.common.dto.user.profile.enums.converter;

import meow.common.dto.user.profile.enums.TypeTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTargetConverterTest {
    private final TypeTargetConverter converter = new TypeTargetConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        assertEquals("Ganar 0,5% de peso", converter.convertToDatabaseColumn(TypeTarget.GAIN_WEIGHT_AGGRESSIVELY));
        assertEquals("Ganar 0,35% de peso", converter.convertToDatabaseColumn(TypeTarget.GAIN_WEIGHT_MODERATELY));
        assertEquals("Ganar 0,25% de peso", converter.convertToDatabaseColumn(TypeTarget.GAIN_WEIGHT_SLOWLY));
        assertEquals("Mantener peso", converter.convertToDatabaseColumn(TypeTarget.MAINTAIN_WEIGHT));
        assertEquals("Perder 0,25% de peso", converter.convertToDatabaseColumn(TypeTarget.LOSE_WEIGHT_SLOWLY));
        assertEquals("Perder 0,5% de peso", converter.convertToDatabaseColumn(TypeTarget.LOSE_WEIGHT_MODERATELY));
        assertEquals("Perder 0,75% de peso", converter.convertToDatabaseColumn(TypeTarget.LOSE_WEIGHT_AGGRESSIVELY));
    }

    @Test
    void shouldConvertToEntityAttribute() {
        assertEquals(TypeTarget.GAIN_WEIGHT_AGGRESSIVELY, converter.convertToEntityAttribute("Ganar 0,5% de peso"));
        assertEquals(TypeTarget.GAIN_WEIGHT_MODERATELY, converter.convertToEntityAttribute("Ganar 0,35% de peso"));
        assertEquals(TypeTarget.GAIN_WEIGHT_SLOWLY, converter.convertToEntityAttribute("Ganar 0,25% de peso"));
        assertEquals(TypeTarget.MAINTAIN_WEIGHT, converter.convertToEntityAttribute("Mantener peso"));
        assertEquals(TypeTarget.LOSE_WEIGHT_SLOWLY, converter.convertToEntityAttribute("Perder 0,25% de peso"));
        assertEquals(TypeTarget.LOSE_WEIGHT_MODERATELY, converter.convertToEntityAttribute("Perder 0,5% de peso"));
        assertEquals(TypeTarget.LOSE_WEIGHT_AGGRESSIVELY, converter.convertToEntityAttribute("Perder 0,75% de peso"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de objetivo incorrecto"));
    }

}