package com.thizgroup.promotion.model.converter;

import javax.persistence.AttributeConverter;

public class BooleanStringConverter implements AttributeConverter<Boolean, String> {
  @Override
  public String convertToDatabaseColumn(Boolean attribute) {
    return attribute ? "Y" : "N";
  }

  @Override
  public Boolean convertToEntityAttribute(String dbData) {
    return "Y".equals(dbData);
  }
}
