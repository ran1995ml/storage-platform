package com.ran.storage.platform.core.service.node;

import com.ran.storage.platform.common.bean.entity.node.Node;

/**
 * NodeService
 *
 * @author rwei
 * @since 2023/8/23 17:57
 */
public interface NodeService {
    Long addNodeToCluster(Node node, Long clusterId);

    void removeNodeFromCluster(Node node, Long nodeId);

    Boolean getNodeRunState(Node node);
}
