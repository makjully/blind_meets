package converters;

import model.InterestGeneral;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class UserInterestConverter implements AttributeConverter<InterestGeneral, String> {
    @Override
    public String convertToDatabaseColumn(InterestGeneral attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public InterestGeneral convertToEntityAttribute(String dbItem) {
        return InterestGeneral.valueOf(dbItem.toUpperCase());
    }
}
