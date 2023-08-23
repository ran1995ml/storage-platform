package com.ran.storage.platform.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MainApplication
 *
 * @author rwei
 * @since 2023/8/7 17:43
 */
@SpringBootApplication(scanBasePackages = {"com.ran.storage.platform"})
@MapperScan("com.ran.storage.platform.persist.mysql")
public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication application = new SpringApplication(MainApplication.class);
            application.run(args);
            logger.info("storage-platform started");
        } catch (Exception e) {
            logger.error("Error: Starting storage-platform ", e);
        }
    }
}
