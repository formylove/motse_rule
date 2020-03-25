package ink.moshuier.motse.enums;

import ink.moshuier.motse.enums.util.PersistableEnum;
import lombok.Getter;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum QuarantsEnum implements PersistableEnum<String> {
    CRISIS("CRISIS"),//重要且紧急
    IMPORTANT_AND_NOTURGENT("IMPORTANT_AND_NOTURGENT"),//重要但不紧急
    UNIMPORTANT_AND_URGENT("UNIMPORTANT_AND_URGENT"),//Interruptions and Busy Work
    TIME_WASTERS("TIME_WASTERS");//TimeWasters

    @Getter
    private final String dbValue;

    private QuarantsEnum(String dbValue) {
        this.dbValue = dbValue;
    }


    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<QuarantsEnum, String>
            implements AttributeConverter<QuarantsEnum, String> {
    }

    public static QuarantsEnum getEnumFromName(String name) {
        for (QuarantsEnum e : QuarantsEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static QuarantsEnum getEnumFromDBValue(String dbValue) {
        for (QuarantsEnum e : QuarantsEnum.values()) {
            if (e.getDbValue().equalsIgnoreCase(dbValue)) {
                return e;
            }
        }
        return null;
    }
}
