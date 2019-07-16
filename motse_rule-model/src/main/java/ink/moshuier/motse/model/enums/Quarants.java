package ink.moshuier.motse.model.enums;

import javax.persistence.AttributeConverter;

/**
 * @author : Sarah Xu
 * @date : 2019-04-23
 **/
public enum Quarants implements PersistableEnum<Integer> {
    Crisis(0),//重要且紧急
    ImportantAndNotUrgent(1),//重要但不紧急
    UnimportantAndUrgent(2),//Interruptions and Busy Work
    TimeWasters(3);//TimeWasters

    private final Integer enumKey;

    private Quarants(Integer enumKey) {
        this.enumKey = enumKey;
    }

    public Integer getEnumKey() {
        return enumKey;
    }
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends PersistableEnum.Converter<Quarants, Integer>
            implements AttributeConverter<Quarants, Integer> {}
}
