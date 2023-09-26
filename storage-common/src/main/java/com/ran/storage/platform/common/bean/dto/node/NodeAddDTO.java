package com.ran.storage.platform.common.bean.dto.node;

import lombok.Data;

/**
 * NodeAddDTO
 *
 * @author rwei
 * @since 2023/9/3 21:12
 */
@Data
public class NodeAddDTO {
    private Long clusterId;

    private String host;

    private Integer port;

    private String role;
}
