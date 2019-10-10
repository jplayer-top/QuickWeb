package top.jplayer.common.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.jplayer.common.constant.ExceptionEnums;

/**
 * Created by 蔡超 on 2019/1/19.
 */
@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 2694116434900211324L;

    public static final String RESULT_OK = "ok";

    public static final String RESULT_NOT_OK = "not ok";

    public static final String MESSAGE_SUCCESS = "操作成功";

    public static final String MESSAGE_FALSE = "操作失败";

    private String result;

    private Object data;
    private Object extra;

    private String message;

    private Curson curson;

    private List<Error> erros;

    @Data
    public static class Curson {
        private int total;

        private int offset;

        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String field;

        private String message;
    }

    private static BaseResult createBaseResult(String result, Object data, String success, Curson curson,
                                               List<Error> errors) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setMessage(success);
        baseResult.setCurson(curson);
        baseResult.setErros(errors);
        return baseResult;
    }

    public static BaseResult ok() {
        return createBaseResult(RESULT_OK, null, MESSAGE_SUCCESS, null, null);
    }

    public static BaseResult ok(Object data) {
        return createBaseResult(RESULT_OK, data, MESSAGE_SUCCESS, null, null);
    }

    public static BaseResult notOK(List<Error> errors) {
        return createBaseResult(RESULT_NOT_OK, null, MESSAGE_FALSE, null, errors);
    }

    public static BaseResult notOK(Object data) {
        return createBaseResult(RESULT_NOT_OK, data, MESSAGE_FALSE, null, null);
    }

    public static BaseResult notOK(String code, String message) {
        return createBaseResult(code, null, message, null, null);
    }

    public static BaseResult notOK(ExceptionEnums exceptionEnums) {
        return createBaseResult(exceptionEnums.getCode(), null, exceptionEnums.getMessage(), null, null);
    }

    public static BaseResult notOK(ExceptionEnums exceptionEnums, Object param) {
        return createBaseResult(exceptionEnums.getCode(), null, exceptionEnums.getMessage() + ":" + param,
                null, null);
    }

}
