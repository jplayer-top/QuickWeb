package top.jplayer.quickpay.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Obl on 2019/10/8.
 * top.jplayer.quickpay.interceptor
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//请求路劲
        String url = request.getRequestURI();
//获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
//获取请求客户都IP
        String clientIp = request.getRemoteAddr();
        String methodName = request.getMethod();
        String requestType = request.getHeader("X-Requested-With");

        StringBuffer content = new StringBuffer();
        content.append("url=" + url);
        content.append(" ,paramData=").append(paramData);
        content.append(", clientIp=").append(clientIp);
        content.append(" ,HTTP_METHOD=").append(methodName);
        System.out.println("拦截器方式：" + content);
        request.setAttribute("STARTTIME", System.currentTimeMillis());
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int status = response.getStatus();
        long currentTime = System.currentTimeMillis();
        long startTime = Long.valueOf(request.getAttribute("STARTTIME").toString());
        System.out.println("拦截器方式请求耗时：" + (currentTime - startTime) + "ms");
    }
}
