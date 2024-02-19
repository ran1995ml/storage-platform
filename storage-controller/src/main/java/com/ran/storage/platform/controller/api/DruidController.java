package com.ran.storage.platform.controller.api;

import com.ran.storage.platform.common.bean.dto.ClusterAddDTO;
import com.ran.storage.platform.common.bean.entity.Cluster;
import com.ran.storage.platform.common.bean.entity.Node;
import com.ran.storage.platform.common.bean.entity.Result;
import com.ran.storage.platform.common.bean.po.ClusterPO;
import com.ran.storage.platform.common.bean.vo.ClusterVO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.ClusterService;
import com.ran.storage.platform.core.service.ConfigService;
import com.ran.storage.platform.core.service.NodeService;
import com.ran.storage.platform.core.service.impl.DruidConfigServiceImpl;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * DruidController
 *
 * @author rwei
 * @since 2024/2/5 13:58
 */
@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX)
public class DruidController {
    @Autowired
    private NodeService nodeService;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private ConfigService configService;

    @PostMapping(value = "/druid/cluster/add")
    @ResponseBody
    public Result<Long> addDruidCluster(@RequestBody ClusterAddDTO clusterAddDTO) throws IOException, ApiException {
        ClusterPO clusterPO = ConvertUtils.obj2Obj(clusterAddDTO, ClusterPO.class);
        return Result.buildSuccess(clusterService.addCluster(clusterPO));
    }

    @GetMapping(value = "/druid/cluster")
    @ResponseBody
    public Result<List<ClusterVO>> ListAllDruidCluster() {
        List<Cluster> clusters = clusterService.listAllClusters();
        return Result.buildSuccess(ConvertUtils.list2List(clusters, ClusterVO.class));
    }

    @GetMapping(value = "/druid/cluster/{clusterName}")
    @ResponseBody
    public Result<ClusterVO> getDruidClusterBasic(@PathVariable String clusterName) {
        Cluster cluster = clusterService.getClusterByName(clusterName);
        return Result.buildSuccess(ConvertUtils.obj2Obj(cluster, ClusterVO.class));
    }

    @GetMapping(value = "/druid/config/{clusterName}")
    @ResponseBody
    public Result<String> getDruidClusterConfig(@PathVariable String clusterName) throws IOException, ApiException {
        String config = configService.getLatestConfigFromCluster(clusterName);
        return Result.buildSuccess(config);
    }

    @GetMapping(value = "/druid/node/{clusterName}")
    @ResponseBody
    public Result<List<Node>> getAllNodesOfCluster(@PathVariable String clusterName) throws IOException, ApiException {
        List<Node> nodes = nodeService.listAllNodesFromDB(clusterName);
        return Result.buildSuccess(nodes);
    }

    @PostMapping(value = "/druid/node/{clusterName}/update")
    @ResponseBody
    public Result<Void> UpdateNodesOfCluster(@PathVariable String clusterName) throws IOException, ApiException {
        nodeService.updateNodesToDB(clusterName);
        return Result.buildSuccess();
    }
}
