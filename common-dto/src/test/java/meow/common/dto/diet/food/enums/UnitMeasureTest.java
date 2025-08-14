package meow.common.dto.diet.food.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitMeasureTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("gr", UnitMeasure.GR.getValue());
        assertEquals("ml", UnitMeasure.ML.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        assertEquals(UnitMeasure.GR, UnitMeasure.fromValue(String.valueOf(UnitMeasure.GR))); // mayúsculas
        assertEquals(UnitMeasure.ML, UnitMeasure.fromValue(String.valueOf(UnitMeasure.ML)));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                UnitMeasure.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Unidad inválida"));
    }
}