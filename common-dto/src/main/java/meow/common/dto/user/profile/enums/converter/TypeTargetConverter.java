package meow.common.dto.user.profile.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import meow.common.dto.user.profile.enums.TypeTarget;

@Converter(autoApply = true)
public class TypeTargetConverter implements AttributeConverter<TypeTarget, String> {

    @Override
    public String convertToDatabaseColumn(TypeTarget attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public TypeTarget convertToEntityAttribute(String dbData) {
        return dbData != null ? TypeTarget.fromValue(dbData) : null;
    }
}
