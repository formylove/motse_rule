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
    public static final Boolean DEFAULT_IGNORE_IF_EMPTY = true;
    public static final Boolean DEFAULT_IGNORE_IF_NULL = true;
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
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfNull(DEFAULT_IGNORE_IF_NULL).ignoreIfEmpty(DEFAULT_IGNORE_IF_EMPTY).operator(DEFAULT_OPERATOR).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfNull) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfNull(ignoreIfNull).ignoreIfEmpty(DEFAULT_IGNORE_IF_EMPTY).operator(DEFAULT_OPERATOR).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfNull, CriteriaOperator operator) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfNull(ignoreIfNull).ignoreIfEmpty(DEFAULT_IGNORE_IF_EMPTY).operator(operator).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public SearchConditionListBuilder add(String name, Object value, Boolean ignoreIfEmpty, Boolean ignoreIfNull, CriteriaOperator operator) {
            SearchCondition<Object> searchCondition = SearchCondition.builder().name(name).value(value).ignoreIfNull(ignoreIfNull).ignoreIfEmpty(ignoreIfEmpty).operator(operator).build();
            this.conditions.add(searchCondition);
            return this;
        }

        public List<SearchCondition> end() {
            return conditions;
        }
    }


}
