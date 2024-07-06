package com.lib.utils;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configurable
public class WebMvcConfig implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/book/{bookId}","/comment/**","/remmend/detail/{bookId}","/books/popular","/books/categorys?categoryId={categoryId}");
    }
}
