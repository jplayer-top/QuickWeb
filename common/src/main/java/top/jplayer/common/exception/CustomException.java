package top.jplayer.common.exception;


import top.jplayer.common.constant.ExceptionEnums;

/**
 * 自定义异常封装类
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 7024390051745547450L;

    private String code;

    public CustomException() {
        super();
    }

    /**
     * 最终转化形式
     */
    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 只接受异常枚举
     * 
     * @param exceptionEnums 异常枚举
     */
    public CustomException(ExceptionEnums exceptionEnums) {
        this(exceptionEnums.getCode(), exceptionEnums.getMessage());
    }

    public CustomException(ExceptionEnums exceptionEnums, String message) {
        this(exceptionEnums.getCode(), message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
