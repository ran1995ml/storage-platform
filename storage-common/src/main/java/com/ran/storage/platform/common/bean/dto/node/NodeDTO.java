package com.ran.storage.platform.common.bean.dto.node;

import lombok.Data;

/**
 * NodeDTO
 *
 * @author rwei
 * @since 2023/9/3 21:32
 */
@Data
public class NodeDTO {
    private Long clusterId;

    private String host;

    private Integer port;

    private String role;
}
