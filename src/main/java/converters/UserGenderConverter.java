package converters;

import model.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class UserGenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.valueOf(dbData.toUpperCase());
    }
}