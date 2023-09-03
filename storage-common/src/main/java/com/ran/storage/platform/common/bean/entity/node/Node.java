package com.ran.storage.platform.common.bean.entity.node;

import lombok.Data;

/**
 * Node
 *
 * @author rwei
 * @since 2023/8/23 17:43
 */
@Data
public class Node {
    protected Long id;

    protected Long clusterId;

    protected String host;

    protected Integer port;

    protected String role;
}
