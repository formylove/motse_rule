package ink.moshuier.motse.enums;


import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum TaskType implements PersistableEnum<Integer> {
    SINGLE(0),//当天任务
    HABIT(1),//习惯
    PROJECT(2);//项目

    private final Integer enumKey;

    private TaskType(Integer enumKey) {
        this.enumKey = enumKey;
    }

    @Override
    public Integer getEnumKey() {
        return enumKey;
    }

    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<TaskType, Integer>
            implements AttributeConverter<TaskType, Integer> {
    }


    public static TaskType getEnumFromName(String name) {
        for (TaskType e : TaskType.values()) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
