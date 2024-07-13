package com.lib.utils;

import com.lib.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
@Configurable
public class WebMvcConfig implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor((MemberRepository) jwtInterceptor))
                .addPathPatterns("/**")
                .excludePathPatterns("/book/{bookId}","/comment/**","/remmend/detail/{bookId}","/books/popular","/books/categorys?categoryId={categoryId}");
    }
}
