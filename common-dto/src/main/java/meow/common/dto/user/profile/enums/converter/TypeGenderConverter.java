package meow.common.dto.user.profile.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import meow.common.dto.user.profile.enums.TypeGender;

@Converter(autoApply = true)
public class TypeGenderConverter implements AttributeConverter<TypeGender, String>  {

    @Override
    public String convertToDatabaseColumn(TypeGender attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public TypeGender convertToEntityAttribute(String dbData) {
        return dbData != null ? TypeGender.fromValue(dbData) : null;
    }
}
