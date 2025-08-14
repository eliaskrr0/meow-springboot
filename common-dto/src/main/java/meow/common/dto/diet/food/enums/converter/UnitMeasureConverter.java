package meow.common.dto.diet.food.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import meow.common.dto.diet.food.enums.UnitMeasure;

@Converter(autoApply = true)
public class UnitMeasureConverter implements AttributeConverter<UnitMeasure, String> {

    @Override
    public String convertToDatabaseColumn(UnitMeasure attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public UnitMeasure convertToEntityAttribute(String dbData) {
        return dbData != null ? UnitMeasure.fromValue(dbData) : null;
    }
}
