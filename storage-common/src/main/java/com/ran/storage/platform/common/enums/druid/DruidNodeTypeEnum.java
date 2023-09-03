package com.ran.storage.platform.common.enums.druid;

/**
 * DruidNodeTypeEnum
 *
 * @author rwei
 * @since 2023/8/23 17:22
 */
public enum DruidNodeTypeEnum {
    COORDINATOR("coordinator"),

    OVERLORD("overlord"),

    ROUTER("router"),

    BROKER("broker"),

    HISTORICAL("historical"),

    MIDDLE_MANAGER("middleManager")
    ;

    private final String msg;

    DruidNodeTypeEnum(String msg) {
        this.msg = msg;
    }

}
