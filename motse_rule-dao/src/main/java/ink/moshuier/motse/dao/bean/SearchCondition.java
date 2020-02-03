package ink.moshuier.motse.dao.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SearchCondition<T> {
    //the property name of class
    String name;
    //the value used to compare
    T value;
    //ignore if the value string is empty
    Boolean ignoreIfEmpty = false;
    //ignore if the value object is null
    Boolean ignoreIfNull = false;
    //operator for comparing
    CriteriaOperator operator = CriteriaOperator.EQUEL;

    public SearchCondition(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public SearchCondition(String name, T value, CriteriaOperator criteriaOperator) {
        this.name = name;
        this.value = value;
        this.operator = criteriaOperator;
    }

    public static List<SearchCondition> buildConditionList(String key, Object value) {
        List<SearchCondition> conditions = Arrays.asList(new SearchCondition(key, value));
        return conditions;
    }

    public static List<SearchCondition> buildConditionList(String key, Object value, CriteriaOperator criteriaOperator) {
        List<SearchCondition> conditions = Arrays.asList(new SearchCondition(key, value, criteriaOperator));
        return conditions;
    }

    public enum CriteriaOperator {
        LIKE,
        IN,
        EQUEL
    }
}
