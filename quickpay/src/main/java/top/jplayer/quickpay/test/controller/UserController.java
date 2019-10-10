package top.jplayer.quickpay.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequestWrapper;

import top.jplayer.common.dto.BaseResult;
import top.jplayer.quickpay.test.service.IUserService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Author
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/test/user")
public class UserController {
    @Autowired
    IUserService mUserService;

    @GetMapping("get")
    public BaseResult getUser(@RequestHeader String customerId) {
        return BaseResult.ok(customerId);
    }

    @GetMapping("set")
    public BaseResult setUser(HttpServletRequestWrapper requestWrapper) {
        return BaseResult.ok(requestWrapper);
    }

    @GetMapping("set1")
    public BaseResult setUser1() {
        createException();
        return BaseResult.ok();
    }

    private void createException() {
        int i = 5 / 0;
    }
}
