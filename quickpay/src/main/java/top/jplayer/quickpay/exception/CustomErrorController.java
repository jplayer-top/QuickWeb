package top.jplayer.quickpay.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.jplayer.common.dto.BaseResult;

/**
 * Created by Obl on 2019/10/9.
 * top.jplayer.common.exception
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@RestController
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public BaseResult error() {
        return BaseResult.notOK("404","访问路径错误");
    }
}
