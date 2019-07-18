package ink.moshuier.motse.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.MapperFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@Slf4j
abstract class AbstractJsonConverter<T> implements AttributeConverter<T, String> {

  private static final ObjectMapper JSON =
      new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
          .configure(DEFAULT_VIEW_INCLUSION, true)
          .disable(WRITE_DATES_AS_TIMESTAMPS)
          .registerModule(new JavaTimeModule());

  @Override
  public String convertToDatabaseColumn(T attribute) {
    try {
      return (null == attribute) ? null : JSON.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      log.error("ObjectToJsonString error", e);
      return null;
    }
  }

  @Override
  public T convertToEntityAttribute(String dbData) {
    if (null == dbData || dbData.isEmpty()) {
      return null;
    }
    Type superclass = this.getClass().getGenericSuperclass();
    if (superclass instanceof Class) {
      log.error("Missing type parameter.");
      throw new RuntimeException("Missing type parameter.");
    }
    ParameterizedType parameterizedType = (ParameterizedType) superclass;

    try {
      JavaType javaType = JSON.getTypeFactory().constructType(parameterizedType);
      return JSON.readValue(dbData, javaType);
    } catch (Throwable e) {
      log.error("jsonStringToObject error", e);
      return null;
    }
  }
}
