package meow.micromanagerfood.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitMeasureTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("gr", UnitMeasure.GR.getValue());
        assertEquals("ml", UnitMeasure.ML.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        assertEquals(UnitMeasure.GR, UnitMeasure.fromValue("gr"));
        assertEquals(UnitMeasure.GR, UnitMeasure.fromValue("GR")); // mayúsculas
        assertEquals(UnitMeasure.ML, UnitMeasure.fromValue("ml"));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                UnitMeasure.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Unidad inválida"));
    }

    @Test
    void valueOf() {
    }
}