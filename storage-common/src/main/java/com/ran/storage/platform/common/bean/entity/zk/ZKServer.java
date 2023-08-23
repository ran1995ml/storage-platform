package com.ran.storage.platform.common.bean.entity.zk;

import lombok.Data;

/**
 * ZKServer
 *
 * @author rwei
 * @since 2023/8/2 15:30
 */
@Data
public class ZKServer {
    private String host;

    private Integer port;

    private String version;

    private Integer status;

    private String role;
}
