package meow.micromanagerfood.model.enums.converter;

import meow.micromanagerfood.model.enums.UnitMeasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitMeasureConverterTest {

    private final UnitMeasureConverter converter = new UnitMeasureConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        assertEquals(UnitMeasure.GR, converter.convertToDatabaseColumn(UnitMeasure.GR));
        assertEquals(UnitMeasure.ML, converter.convertToDatabaseColumn(UnitMeasure.ML));
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void shouldConvertToEntityAttribute() {
        assertEquals(UnitMeasure.GR, converter.convertToEntityAttribute(String.valueOf(UnitMeasure.GR)));
        assertEquals(UnitMeasure.ML, converter.convertToEntityAttribute(String.valueOf(UnitMeasure.ML)));
        assertEquals(UnitMeasure.GR, converter.convertToEntityAttribute(String.valueOf(UnitMeasure.GR))); // case-insensitive
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute("kg")
        );
        assertTrue(exception.getMessage().contains("Unidad inválida"));
    }
}