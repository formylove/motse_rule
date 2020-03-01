package ink.moshuier.motse.enums;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum QuarantsEnum implements PersistableEnum<Integer> {
    CRISIS(0),//重要且紧急
    IMPORTANT_AND_NOTURGENT(1),//重要但不紧急
    UNIMPORTANT_AND_URGENT(2),//Interruptions and Busy Work
    TIME_WASTERS(3);//TimeWasters

    private final Integer enumKey;

    private QuarantsEnum(Integer enumKey) {
        this.enumKey = enumKey;
    }

    @Override
    public Integer getEnumKey() {
        return enumKey;
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<QuarantsEnum, Integer>
            implements AttributeConverter<QuarantsEnum, Integer> {
    }

    public static QuarantsEnum getEnumFromName(String name) {
        for (QuarantsEnum e : QuarantsEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
