package com.ran.storage.platform.controller.api.cluster;

import com.ran.storage.platform.common.bean.dto.cluster.ClusterAddDTO;
import com.ran.storage.platform.common.bean.entity.cluster.Cluster;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import com.ran.storage.platform.common.bean.vo.cluster.ClusterBaseVO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClusterController
 *
 * @author rwei
 * @since 2023/11/24 14:45
 */
@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX)
public class ClusterController {
    private static final Logger logger = LoggerFactory.getLogger(ClusterController.class);

    @Autowired
    private ClusterService clusterService;

    @PostMapping(value = "cluster")
    @ResponseBody
    public Result<Long> addCluster(@RequestBody ClusterAddDTO clusterAddDTO) {
        return Result.buildSuccess(clusterService.addCluster(ConvertUtils.obj2Obj(clusterAddDTO, ClusterPO.class)));
    }

    @GetMapping(value = "cluster/{clusterId}/basic")
    @ResponseBody
    public Result<ClusterBaseVO> getClusterBasic(@PathVariable Long clusterId) {
        Cluster cluster = clusterService.getClusterById(clusterId);
        return Result.buildSuccess(ConvertUtils.obj2Obj(cluster, ClusterBaseVO.class));
    }
}
