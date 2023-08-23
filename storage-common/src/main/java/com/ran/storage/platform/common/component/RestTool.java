package com.ran.storage.platform.common.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * RestTool
 *
 * @author rwei
 * @since 2023/8/4 17:35
 */
@Component
public class RestTool {
    private static final Logger logger = LoggerFactory.getLogger(RestTool.class);

    @Autowired
    private RestTemplate restTemplate;

    public <T> T getForObject(String url, Map<String, String> headers, Class<T> type) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(null, getJsonContentHeaders(headers)), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody(), type);
        } catch (Exception e) {
            logger.error("Error sending get request to url: {}", url, e);
        }
        return null;
    }

    public <T> T getForObject(String url, Map<String, String> headers, TypeReference<T> resultType) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(null, getJsonContentHeaders(headers)), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody(), resultType);
        } catch (Exception e) {
            logger.error("Error sending get request to url: {}", url, e);
        }
        return null;
    }

    private HttpHeaders getJsonContentHeaders(Map<String, String> headerMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        for (Map.Entry<String, String> entry: headerMap.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        return headers;
    }
}
