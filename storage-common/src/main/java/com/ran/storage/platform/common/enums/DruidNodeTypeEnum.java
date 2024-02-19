package com.ran.storage.platform.common.enums;

import java.util.Arrays;

/**
 * DruidNodeTypeEnum
 *
 * @author rwei
 * @since 2024/2/18 16:31
 */
public enum DruidNodeTypeEnum {
    COORDINATOR("coordinator"),

    OVERLORD("overlord"),

    MIDDLEMANAGER("middlemanager"),

    HISTORICAL("historical"),

    BROKER("broker"),

    ROUTER("router")
    ;

    private final String type;

    DruidNodeTypeEnum(String type) {
        this.type = type;
    }

    public static boolean contains(String str) {
        return Arrays.stream(DruidNodeTypeEnum.values())
                .anyMatch(value->str.toUpperCase().contains(value.name()));
    }
}
