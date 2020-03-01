package ink.moshuier.motse.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        String db = null;
        if (Objects.nonNull(attribute)) {
            db = attribute ? "Y" : "N";
        }
        return db;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
