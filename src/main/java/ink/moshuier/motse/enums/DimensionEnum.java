package ink.moshuier.motse.enums;

import ink.moshuier.motse.enums.util.PersistableEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum DimensionEnum implements PersistableEnum<String> {
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

    DimensionEnum(String dbValue, String baseLine) {
        this.dbValue = dbValue;
        this.baseLine = baseLine;
    }


    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<DimensionEnum, String>
            implements AttributeConverter<DimensionEnum, String> {
    }

    public static DimensionEnum getEnumFromName(String name) {
        for (DimensionEnum e : DimensionEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static DimensionEnum getEnumFromDBValue(String dbValue) {
        for (DimensionEnum e : DimensionEnum.values()) {
            if (e.getDbValue().equalsIgnoreCase(dbValue)) {
                return e;
            }
        }
        return null;
    }
}
