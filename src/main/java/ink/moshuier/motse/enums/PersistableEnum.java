package ink.moshuier.motse.enums;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface PersistableEnum<DB extends Serializable> {
    DB getEnumKey();

    default String getFullPath() {
        return new StringBuffer(this.getClass().getName())
                .append(".")
                .append(this.toString())
                .toString();
    }

    abstract class Converter<ENUM extends PersistableEnum<DB>, DB extends Serializable> {

        public final DB convertToDatabaseColumn(ENUM attribute) {
            return (null == attribute) ? null : attribute.getEnumKey();
        }

        public final ENUM convertToEntityAttribute(DB dbData) {
            if (null != dbData) {
                Type superclass = this.getClass().getGenericSuperclass();
                if (superclass instanceof Class) {
                    throw new RuntimeException("Missing type parameter.");
                }
                ParameterizedType parameterizedType = (ParameterizedType) superclass;

                @SuppressWarnings("unchecked")
                Class<ENUM> enumClass = (Class<ENUM>) parameterizedType.getActualTypeArguments()[0];

                List<ENUM> findResult =
                        Arrays.stream(enumClass.getEnumConstants())
                                .filter(enumInstance -> dbData.equals(enumInstance.getEnumKey()))
                                .collect(toList());
                if (findResult.size() != 1) {
                    if (findResult.size() > 1) {
                        throw new IllegalArgumentException(
                                "Persistent enum has duplicated value: "
                                        + dbData.toString()
                                        + ", for "
                                        + findResult.size()
                                        + " times.");
                    }
                    throw new IllegalArgumentException(
                            "Persistent enum has not defined this value: " + dbData.toString());
                }

                return findResult.get(0);
            }
            return null;
        }
    }
}
