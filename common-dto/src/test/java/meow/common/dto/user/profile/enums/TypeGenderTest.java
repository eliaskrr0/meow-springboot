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
        assertEquals(TypeGender.MASCULINE, TypeGender.fromValue(String.valueOf(TypeGender.MASCULINE)));
        assertEquals(TypeGender.FEMININE, TypeGender.fromValue(String.valueOf(TypeGender.FEMININE)));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TypeGender.fromValue("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de genero incorrecto"));
    }
}