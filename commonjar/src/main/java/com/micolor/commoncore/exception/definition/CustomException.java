package com.micolor.commoncore.exception.definition;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.exception.definition
 *
 * @Author: Evangoe
 * @Description: 自定义异常
 * @Date:13/06/17
 * @Modified:
 */
public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorCode ="";
    private boolean propertiesKey = true;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String errorCode, String message) {
        this(errorCode, message, true);
    }
    public CustomException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }
    public CustomException(String errorCode, String message, Throwable cause){
        this(errorCode, message, cause, true);
    }
    public CustomException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }
    public CustomException(String message, Throwable cause){
        super(message,cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }
}
