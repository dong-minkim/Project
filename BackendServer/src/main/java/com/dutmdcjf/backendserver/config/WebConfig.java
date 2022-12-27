package com.dutmdcjf.backendserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig {
//public class WebConfig implements WebMvcConfigurer {
    //private final AuthInterceptor authInterceptor;

    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**");
                //.excludePathPatterns(path) 경로 방식이 아닌 어노테이션으로 설정
    }
     */

    /*
     * 혹시 모르는 Cors 대비
     * */
    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(3600);;
    }
     */
}
