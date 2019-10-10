package top.jplayer.quickpay.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import top.jplayer.common.dto.BaseResult;

@Slf4j
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandlerAdvice {
    @ExceptionHandler({Throwable.class, Exception.class})
    public BaseResult handleControllerException(Throwable ex)  {
        log.error("统一异常处理：", ex);
        return BaseResult.notOK("500", ex.getMessage());
    }

}
