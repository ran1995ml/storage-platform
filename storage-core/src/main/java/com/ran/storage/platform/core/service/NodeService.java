package com.ran.storage.platform.core.service;

import com.ran.storage.platform.common.bean.entity.Node;
import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;
import java.util.List;

/**
 * NodeService
 *
 * @author rwei
 * @since 2024/2/6 09:55
 */
public interface NodeService {
    String getLogFromNode();

    Node getNodeInfo();

    void updateNodesToDB(String clusterName);

    List<Node> listAllNodesFromDB(String clusterName);

    List<Node> listAllNodesFromCluster(String clusterName) throws IOException, ApiException;
}
