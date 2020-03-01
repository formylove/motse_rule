package ink.moshuier.motse.enums;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum UnitTypeEnum implements PersistableEnum<Integer> {
    LENGTH(0, "纳米"),//长度
    HEIGHT(1, ""),//高度
    TIME(2, "纳秒"),//时间
    WEIGHT(3, "微克"),//重量
    TEMPERATURE(4, "摄氏度"),//温度
    VELOCITY(5, "厘米/秒"),//速度
    DENSITY(6, ""),//密度
    AREA(7, "水的密度"),//面积
    VOLUME(8, "1立方厘米"),//体积
    FORCE(9, "牛"),//力
    CURRENCY(10, "韩元"),//货币
    ;
    private final Integer enumKey;
    private final String baseLine;

    UnitTypeEnum(Integer enumKey, String baseLine) {
        this.enumKey = enumKey;
        this.baseLine = baseLine;
    }

    @Override
    public Integer getEnumKey() {
        return enumKey;
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<UnitTypeEnum, Integer>
            implements AttributeConverter<UnitTypeEnum, Integer> {
    }


    public static UnitTypeEnum getEnumFromName(String name) {
        for (UnitTypeEnum e : UnitTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
