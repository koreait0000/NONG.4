//package com.spring.nong4;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcConfiguration implements WebMvcConfigurer {
//    @Override
//    // 정적 리소스 파일을 제외
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthCheckIntercepter())
//        .excludePathPatterns("/res/**" , "/pic/**", "/error", "favicon.ico");
//    }
//}
