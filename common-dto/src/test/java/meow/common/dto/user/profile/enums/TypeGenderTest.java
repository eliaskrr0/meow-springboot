package meow.common.dto.user.profile.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeGenderTest {

    @Test
    void shouldReturnCorrectJsonValue() {
        assertEquals("Masculino", TypeGender.MASCULINE.getValue());
        assertEquals("Femenino", TypeGender.FEMININE.getValue());
    }

    @Test
    void shouldCreateFromValidValue() {
        assertEquals(TypeGender.MASCULINE, TypeGender.fromValue(TypeGender.MASCULINE.getValue()));
        assertEquals(TypeGender.FEMININE, TypeGender.fromValue(TypeGender.FEMININE.getValue()));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TypeGender.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de genero incorrecto"));
    }
}