package meow.micromanagerfood.model.enums.converter;

import meow.micromanagerfood.model.enums.UnitMeasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitMeasureConverterTest {

    private final UnitMeasureConverter converter = new UnitMeasureConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        assertEquals("gr", converter.convertToDatabaseColumn(UnitMeasure.GR));
        assertEquals("ml", converter.convertToDatabaseColumn(UnitMeasure.ML));
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void shouldConvertToEntityAttribute() {
        assertEquals(UnitMeasure.GR, converter.convertToEntityAttribute("gr"));
        assertEquals(UnitMeasure.ML, converter.convertToEntityAttribute("ml"));
        assertEquals(UnitMeasure.GR, converter.convertToEntityAttribute("GR")); // case-insensitive
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