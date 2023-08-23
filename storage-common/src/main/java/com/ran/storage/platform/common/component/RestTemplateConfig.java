package com.ran.storage.platform.common.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 *
 * @author rwei
 * @since 2023/8/7 15:20
 */
@Configuration
public class RestTemplateConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestTool.class);

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(2 * 60 * 1000);
        simpleClientHttpRequestFactory.setReadTimeout(60 * 1000);

        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory));
        return restTemplate;
    }
}
