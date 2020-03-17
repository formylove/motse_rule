package ink.moshuier.motse.enums;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum UnitDimensionEnum implements PersistableEnum<String> {
    LENGTH("LENGTH", "纳米"),//长度
    HEIGHT("HEIGHT", ""),//高度
    TIME("TIME", "纳秒"),//时间
    WEIGHT("WEIGHT", "微克"),//重量
    TEMPERATURE("TEMPERATURE", "摄氏度"),//温度
    VELOCITY("VELOCITY", "厘米/秒"),//速度
    DENSITY("DENSITY", ""),//密度
    AREA("AREA", "水的密度"),//面积
    VOLUME("VOLUME", "1立方厘米"),//体积
    FORCE("FORCE", "牛"),//力
    CURRENCY("CURRENCY", "韩元"),//货币
    ;
    @Getter
    @Setter
    private final String dbValue;
    @Getter
    @Setter
    private final String baseLine;

    UnitDimensionEnum(String dbValue, String baseLine) {
        this.dbValue = dbValue;
        this.baseLine = baseLine;
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<UnitDimensionEnum, String>
            implements AttributeConverter<UnitDimensionEnum, String> {
    }


    public static UnitDimensionEnum getEnumFromName(String name) {
        for (UnitDimensionEnum e : UnitDimensionEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
