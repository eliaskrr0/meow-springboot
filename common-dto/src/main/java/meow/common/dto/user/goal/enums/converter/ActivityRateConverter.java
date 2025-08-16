package meow.common.dto.user.goal.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import meow.common.dto.user.goal.enums.ActivityRate;

@Converter
public class ActivityRateConverter implements AttributeConverter<ActivityRate, String> {

    @Override
    public String convertToDatabaseColumn(ActivityRate attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public ActivityRate convertToEntityAttribute(String dbData) {
        return dbData != null ? ActivityRate.fromValue(dbData) : null;
    }
}
