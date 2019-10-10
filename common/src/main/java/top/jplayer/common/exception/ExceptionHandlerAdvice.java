package top.jplayer.common.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import top.jplayer.common.constant.ExceptionEnums;
import top.jplayer.common.dto.BaseResult;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler({Throwable.class, Exception.class})
    public BaseResult handleControllerException(Throwable ex) throws Exception {
        log.error("统一异常处理：", ex);
        BaseResult result = new BaseResult();
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            result.setResult(customException.getCode());
            result.setMessage(customException.getMessage());
        } else {
            result.setResult(ExceptionEnums.SYSTEM_EXCEPTION.getCode());
            result.setMessage(ExceptionEnums.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }

}
