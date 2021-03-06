package ink.moshuier.motse.exception;

/**
 * Created by jf on 2019/4/24.
 */
public enum ErrorCode {
    TASK_NOT_FOUND(1000, "task.not.found"),
    INSTANCE_NOT_FOUND(1000, "instance.not.found"),

    ;


    private final Integer code;
    private final String message;

    private ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(Integer code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode.getMessage();
            }
        }
        return null;
    }
}
