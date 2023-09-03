package com.ran.storage.platform.common.bean.entity.cluster;

import com.ran.storage.platform.common.bean.entity.node.Node;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Cluster
 *
 * @author rwei
 * @since 2023/8/2 17:44
 */
@Data
public class Cluster {
    protected Long id;

    protected String name;

    protected String type;

    protected String description;

    protected List<Node> nodes;
}
