package top.jplayer.quickpay.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * Created by Obl on 2019/10/8.
 * top.jplayer.quickpay.interceptor
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@Configuration
public class LogConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> patterns = new ArrayList<>();
        patterns.add("/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns(patterns);
    }
}
