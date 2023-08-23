package com.ran.storage.platform.controller.api.cluster;

import com.ran.storage.platform.common.bean.dto.cluster.DruidClusterAddDTO;
import com.ran.storage.platform.common.bean.dto.cluster.DruidClusterDTO;
import com.ran.storage.platform.common.bean.dto.cluster.DruidClusterUpdateDTO;
import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.DruidClusterPO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.utils.ConvertUtil;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import com.ran.storage.platform.common.converter.ClusterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DruidClusterController
 *
 * @author rwei
 * @since 2023/8/15 11:05
 */
@RestController
@RequestMapping(ApiPrefix.API_DRUID_V1_PREFIX)
public class DruidClusterController {
    @Autowired
    private ClusterService<DruidCluster> clusterService;

    @GetMapping(value = "/cluster/v1/basic")
    public Result<List<DruidCluster>> listAllClusters() {
        List<DruidCluster> druidClusters = clusterService.listAllClusters();
        return Result.buildSuccess(druidClusters);
    }

    @PostMapping(value = "/cluster")
    @ResponseBody
    public Result<Long> addDruidCluster(@RequestBody DruidClusterAddDTO dto) {
        return Result.buildSuccess(clusterService.addCluster(ConvertUtil.obj2Obj(dto, DruidCluster.class)));
    }

    @GetMapping(value = "/cluster/{clusterId}/basic")
    @ResponseBody
    public Result<DruidCluster> getDruidClusterBasic(@PathVariable Long clusterId) {
        return Result.buildSuccess(clusterService.getClusterById(clusterId));
    }

    @DeleteMapping("/cluster")
    @ResponseBody
    public Result<Void> deleteDruidCluster(@RequestParam Long clusterId) {
        clusterService.deleteCluster(clusterId);
        return Result.buildSuccess();
    }

    @PutMapping("/cluster")
    @ResponseBody
    public Result<Void> updateDruidCluster(@RequestBody DruidClusterUpdateDTO druidClusterUpdateDTO) {
        clusterService.updateCluster(ConvertUtil.obj2Obj(druidClusterUpdateDTO, DruidCluster.class));
        return Result.buildSuccess();
    }
}
