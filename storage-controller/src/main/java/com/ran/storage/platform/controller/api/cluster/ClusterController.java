package com.ran.storage.platform.controller.api.cluster;

import com.ran.storage.platform.common.bean.dto.cluster.ClusterAddDTO;
import com.ran.storage.platform.common.bean.dto.cluster.ClusterDTO;
import com.ran.storage.platform.common.bean.dto.node.NodeAddDTO;
import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.entity.node.Node;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import com.ran.storage.platform.common.bean.vo.ClusterVO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import com.ran.storage.platform.core.service.node.NodeService;
import com.ran.storage.platform.core.service.node.impl.DruidNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClusterManageController
 *
 * @author rwei
 * @since 2023/8/25 13:29
 */
@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX)
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @Autowired
    @Qualifier("druidNodeService")
    private NodeService nodeService;

    @GetMapping(value = "cluster/basic")
    public Result<List<ClusterVO>> listAllClusters() {
        List<Cluster> clusters = clusterService.listAllClusters();
        return Result.buildSuccess(ConvertUtils.list2List(clusters, ClusterVO.class));
    }

    @PostMapping(value = "cluster")
    public Result<Long> addCluster(@RequestBody ClusterAddDTO clusterAddDTO) {
        Cluster cluster = ConvertUtils.obj2Obj(clusterAddDTO, Cluster.class);
        return Result.buildSuccess(clusterService.addCluster(cluster));
    }

    @DeleteMapping(value = "cluster")
    public Result<Void> deleteCluster(@RequestParam Long clusterId) {
        clusterService.removeClusterById(clusterId);
        return Result.buildSuccess();
    }

    @PutMapping(value = "cluster")
    public Result<Void> updateCluster(@RequestBody ClusterDTO clusterDTO) {
        clusterService.updateClusterById(ConvertUtils.obj2Obj(clusterDTO, ClusterPO.class));
        return Result.buildSuccess();
    }

    @PostMapping(value = "node")
    public Result<Long> addNode(@RequestBody NodeAddDTO nodeAddDTO) {
        Node node = ConvertUtils.obj2Obj(nodeAddDTO, Node.class);
        Long nodeId = nodeService.addNodeToCluster(node, node.getClusterId());
        return Result.buildSuccess(nodeId);
    }

    @DeleteMapping(value = "node")
    public Result<Void> deleteNode(@RequestParam Long nodeId) {
        nodeService.removeNodeFromClusterById(nodeId);
        return Result.buildSuccess();
    }

    @GetMapping(value = "node/state")
    public Result<Boolean> getNodeRunState(@RequestParam Long nodeId) {
        return Result.buildSuccess(nodeService.getNodeRunState(nodeId));
    }
}
