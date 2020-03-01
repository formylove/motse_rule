package ink.moshuier.motse.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SearchCondition<T> {
    public static final Boolean DEFAULT_IGNOREIFEMPTY = true;
    public static final Boolean DEFAULT_IGNOREIFNULL = true;
    public static final CriteriaOperator DEFAULT_OPERATOR = CriteriaOperator.EQUEL;
    //the property name of class
    String name;
    //the value used to compare
    T value;
    //ignore if the value string is empty
    Boolean ignoreIfEmpty = true;
    //ignore if the value object is null
    Boolean ignoreIfNull = true;
    //operator for comparing
    CriteriaOperator operator = CriteriaOperator.EQUEL;


    public enum CriteriaOperator {
        LIKE,
        IN,
        EQUEL;

        public static CriteriaOperator getEnumFromName(String name) {
            for (CriteriaOperator e : CriteriaOperator.values()) {
                if (e.name().equalsIgnoreCase(name)) {
                    return e;
                }
            }
            return DEFAULT_OPERATOR;
        }
    }

    public static SearchConditionListBuilder begin() {
        return new SearchConditionListBuilder();
    }

    @NoArgsConstructor
    public static class SearchConditionListBuilder {
        List<SearchCondition> conditions = new ArrayList<>();

        public SearchConditionListBuilder add(String name, Object value) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).operator(DEFAULT_OPERATOR).ignoreIfNull(DEFAULT_IGNOREIFNULL).ignoreIfEmpty(DEFAULT_IGNOREIFEMPTY).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfNull) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfEmpty(ignoreIfNull).operator(CriteriaOperator.EQUEL).ignoreIfEmpty(DEFAULT_IGNOREIFEMPTY).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfNull, CriteriaOperator operator) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfEmpty(ignoreIfNull).ignoreIfNull(DEFAULT_IGNOREIFNULL).operator(operator).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfEmpty, Boolean ignoreIfNull, CriteriaOperator operator) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfEmpty(ignoreIfEmpty).ignoreIfNull(ignoreIfNull).operator(operator).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public List<SearchCondition> end() {
            return conditions;
        }
    }


}
