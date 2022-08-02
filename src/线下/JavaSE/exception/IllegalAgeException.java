package exception;

/**
 * 年龄不合法异常
 * <p>
 * 自定义异常
 * 通常用于说明项目中那些因为不满足业务而导致的错误.
 * <p>
 * 自定义异常要做到如下几点:
 * 1:类名要做到见名知意
 * 2:继承自Exception(直接或间接继承)
 * 3:提供超类异常定义的所有构造器
 */
public class IllegalAgeException extends Exception {
    public IllegalAgeException() {
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
