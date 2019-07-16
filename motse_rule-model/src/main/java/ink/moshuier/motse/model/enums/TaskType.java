package ink.moshuier.motse.model.enums;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum TaskType implements PersistableEnum<Integer> {
    Single(0),//当天任务
    Habit(1),//习惯
    Project(2);//项目

    private final Integer enumKey;

    private TaskType(Integer enumKey) {
        this.enumKey = enumKey;
    }

    public Integer getEnumKey() {
        return enumKey;
    }
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<TaskType, Integer>
            implements AttributeConverter<TaskType, Integer> {}
}
