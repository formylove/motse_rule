package ink.moshuier.motse.enums;

import ink.moshuier.motse.enums.util.PersistableEnum;
import lombok.Getter;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
//enum 不支持extends语法，也不支持注解
public enum CardCategoryEnum implements PersistableEnum<String> {
    BIOLOGY("BIOLOGY"),//生物
    CATE("CATE"),//美食
    CAR("CAR");//美食

    @Getter
    private String dbValue;

    private CardCategoryEnum(String dbValue) {
        this.dbValue = dbValue;
    }


    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<CardCategoryEnum, String>
            implements AttributeConverter<CardCategoryEnum, String> {
    }

    public static CardCategoryEnum getEnumFromName(String name) {
        for (CardCategoryEnum e : CardCategoryEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static CardCategoryEnum getEnumFromDBValue(String dbValue) {
        for (CardCategoryEnum e : CardCategoryEnum.values()) {
            if (e.getDbValue().equalsIgnoreCase(dbValue)) {
                return e;
            }
        }
        return null;
    }
}
