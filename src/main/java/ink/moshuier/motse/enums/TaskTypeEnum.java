package ink.moshuier.motse.enums;


import ink.moshuier.motse.enums.util.PersistableEnum;
import lombok.Getter;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum TaskTypeEnum implements PersistableEnum<String> {
    SINGLE("SINGLE"),//当天任务
    HABIT("HABIT"),//习惯
    PROJECT("PROJECT");//项目

    @Getter
    private final String dbValue;

    private TaskTypeEnum(String dbValue) {
        this.dbValue = dbValue;
    }


    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<TaskTypeEnum, String>
            implements AttributeConverter<TaskTypeEnum, String> {
    }


    public static TaskTypeEnum getEnumFromName(String name) {
        for (TaskTypeEnum e : TaskTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static TaskTypeEnum getEnumFromDBValue(String dbValue) {
        for (TaskTypeEnum e : TaskTypeEnum.values()) {
            if (e.getDbValue().equalsIgnoreCase(dbValue)) {
                return e;
            }
        }
        return null;
    }
}
