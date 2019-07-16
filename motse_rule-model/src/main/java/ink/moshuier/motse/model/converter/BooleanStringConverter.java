package ink.moshuier.motse.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.Optional;

@Converter(autoApply = true)
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {
  @Override
  public String convertToDatabaseColumn(Boolean attribute) {
    String db = null;
    if(Objects.nonNull(attribute)){
      db = attribute ? "Y" : "N";
    }
    return db;
  }

  @Override
  public Boolean convertToEntityAttribute(String dbData) {
    return "Y".equals(dbData);
  }
}
