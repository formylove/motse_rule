package com.thizgroup.promotion.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;

@Converter(autoApply = true)
public class InstantConverter implements AttributeConverter<Instant, Long> {

  @Override
  public Long convertToDatabaseColumn(Instant attribute) {
    return null == attribute ? null : attribute.toEpochMilli();
  }

  @Override
  public Instant convertToEntityAttribute(Long dbData) {
    return null == dbData ? null : Instant.ofEpochMilli(dbData);
  }
}
