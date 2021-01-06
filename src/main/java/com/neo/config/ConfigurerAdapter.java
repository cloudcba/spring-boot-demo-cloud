package com.neo.config;


import com.neo.interceptor.LogInterceptor;
import com.neo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigurerAdapter implements WebMvcConfigurer {

    //设置排除路径，spring boot 2.*，注意排除掉静态资源的路径，不然静态资源无法访问
    private final String[] excludePath = {"/list"};


//    @Autowired
//    private TimeInterceptor timeInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 添加日志的拦截器
         */
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
        //registry.addInterceptor(timeInterceptor);
    }
}
