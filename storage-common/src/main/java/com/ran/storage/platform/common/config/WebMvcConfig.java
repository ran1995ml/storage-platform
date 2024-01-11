package com.ran.storage.platform.common.config;

import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.common.component.TokenInterceptor;
import com.ran.storage.platform.common.constant.ApiPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 *
 * @author rwei
 * @since 2023/12/27 14:27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    private static final String ADD_PATH_PATTERNS = String.format("%s/**", ApiPrefix.API_V1_PREFIX);

    private static final String EXCLUDE_PATH_PATTERN = String.format("%s/user/login", ApiPrefix.API_V1_PREFIX);

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.OPTIONS.name(),
                        HttpMethod.DELETE.name(), HttpMethod.POST.name(), HttpMethod.PUT.name())
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);
        interceptorRegistration.addPathPatterns(ADD_PATH_PATTERNS);
        interceptorRegistration.excludePathPatterns(EXCLUDE_PATH_PATTERN);
    }
}
