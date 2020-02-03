package ink.moshuier.motse.model.enums;

import lombok.Getter;
import lombok.Setter;

public enum ConvertDirection {
    FROM_BEAN_TO_ENTITY(""),
    FROM_BEAN_TO_REQUEST(""),
    FROM_ENTITY_TO_BEAN(""),
    FROM_REQUEST_TO_BEAN(""),
    ALL_DIRECTION(""),
    ;

    @Getter
    @Setter
    private String value;


    ConvertDirection(String value) {
        this.value = value;
    }
}
