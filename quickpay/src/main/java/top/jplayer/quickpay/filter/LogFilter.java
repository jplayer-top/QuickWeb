package top.jplayer.quickpay.filter;



import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import top.jplayer.common.constant.ExceptionEnums;
import top.jplayer.common.dto.BaseResult;
import top.jplayer.common.util.IpUtil;
import top.jplayer.common.util.RequestWrapper;
import top.jplayer.common.util.ResponseWrapper;

import static com.sun.org.apache.bcel.internal.classfile.Utility.printArray;


@Slf4j
@WebFilter(filterName = "logFilter", value = {"/*"})
public class LogFilter implements Filter {
    //不过滤的url
    private final String[] excludedPageArray = {};
    private final String[] otherUrl = {"/uncheck1/uncheck2",
            "/custom/uncheck1/uncheck2/platDataOpen",
            "/custom/uncheck1/uncheck2/realOrderDataToCustom",
            "/custom/uncheck1/uncheck2/getCustomResult",
            "/uncheck1/uncheck2/clearOrderNotify"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestHead = getRequestHead(httpServletRequest);
        String contentType = httpServletRequest.getHeader("content-type");
        String parameters = getParameters(httpServletRequest);
        String requestBody;
        HttpServletRequestWrapper requestWrapper;
        String url = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        String ip = IpUtil.getIpAddr(httpServletRequest);

        //判断是否在过滤url之外
        boolean isFilter = true;
        for (String page : excludedPageArray) {
            if(url.contains(page)){
                isFilter = false;
                break;
            }
        }

        if(isFilter){
            //TODO 如果request中包含文件会有问题，1、现在日志打印不出来，2构造RequestWrapper导致重复读取request中内容报错
            if(StringUtils.isNotBlank(contentType) && contentType.contains("application/json")){
                requestWrapper = new RequestWrapper(httpServletRequest);
                requestBody = getRequestBody(requestWrapper);
            }else if(StringUtils.isNotBlank(contentType) && contentType.contains("application/x-www-form-urlencoded")){
                requestWrapper = new RequestWrapper(httpServletRequest);
                requestBody = getRequestBody(requestWrapper);
            }else{
                requestWrapper = new RequestWrapper(httpServletRequest);
                requestBody = getRequestBody(requestWrapper);
                log.error("content-type 异常没有进行处理");
            }
            log.info("\n===============请求开始 \n uri: {}\n method: {} \n client: {} \n requestHead:{} \n requestBody:{} \n parameters:{} \n" +
                            "===============请求开始\n"
                    ,url,method,ip,requestHead,requestBody,parameters);

            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

            filterChain.doFilter(requestWrapper,responseWrapper);

            String responseBody = responseWrapper.getResponseData("UTF-8");

            try {
                boolean otherFlag = false;
                JSONObject jsonObject = JSONObject.parseObject(responseBody);
                for (String page : otherUrl) {
                    if(url.contains(page)){
                        otherFlag = true;
                    }
                }
                log.info(otherFlag+"");
                if(!otherFlag){
                    if(jsonObject == null || StringUtils.isBlank((String)jsonObject.get("result"))){
                        //返回的异常信息，不是规格格式
                        log.error("返回的异常信息，不是规格格式:" + responseBody);
                        responseBody = JSONObject.toJSONString(BaseResult.notOK(ExceptionEnums.SYSTEM_EXCEPTION));
                    }
                }
            } catch (Exception e) {
                log.error("===============返回的异常信息请求参数： \n uri: {}\n method: {} \n client: {} \n requestHead:{} \n requestBody:{} \n " +
                                "parameters:{} \n responseBody:{} \n" +
                                "=====================请求开始================================"
                        ,url,method,ip,requestHead,requestBody,parameters,responseBody);
                log.error("返回的异常信息:",e);
            }
            //排除null
//            responseBody = responseBody.replaceAll(",\"\\w+\":null", "");
            servletResponse.getOutputStream().write(responseBody.getBytes());

            log.info("===============请求结束 \n uri: {} \n method: {} \n client: {} \n requestHead:{} \n requestBody:{} \n parameters:{} " +
                            "\n duration: {}ms \n responseBody:{} \n=====================请求结束================================"
                    ,url, method, ip,requestHead,requestBody,parameters,
                    System.currentTimeMillis() - start,responseBody);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }



    @Override
    public void destroy() {
        log.info("destroy......");
    }


    /**
     * @Description: 获取请求Head
     * @Param:
     * @Return:
     * @Author: caichao
     * @Date: 2019/5/29 14:05
     */
    private String getRequestHead(HttpServletRequest httpServletRequest){
        StringBuilder requestHead = new StringBuilder();
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            requestHead.append("[" + key + " : " + value + "]");
        }
        return requestHead.toString();
    }

    /**
     * @Description:  获取请求参数
     * @Param:
     * @Return:
     * @Author: caichao
     * @Date: 2019/5/29 14:00
     */
    private String getRequestBody(ServletRequest servletRequest){
        String requestBody = "";
        try {

            InputStream inputStream = servletRequest.getInputStream();
            requestBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error("获取请求参数异常：",e);
        }
        return requestBody;
    }

    private String getParameters(ServletRequest servletRequest) {
        StringBuilder parameters = new StringBuilder();
        Map<String, String[]> map = servletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            parameters.append("[" + entry.getKey() + "=" + printArray(entry.getValue()) + "]");
        }
        return parameters.toString();
    }

}
