package com.ran.storage.platform.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ran.storage.platform.common.bean.entity.Cluster;
import com.ran.storage.platform.common.bean.po.ClusterPO;
import com.ran.storage.platform.common.component.KubernetesTool;
import com.ran.storage.platform.common.constant.DruidConstant;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.core.service.ClusterService;
import com.ran.storage.platform.core.service.ConfigService;
import com.ran.storage.platform.persist.mysql.ClusterDAO;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DruidConfigServiceImpl
 *
 * @author rwei
 * @since 2024/2/5 23:06
 */
@Service
public class DruidConfigServiceImpl implements ConfigService {
    @Autowired
    private KubernetesTool kubernetesTool;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private ClusterDAO clusterDAO;

    @Override
    public Boolean updateConfig() {
        return null;
    }

    @Override
    public Boolean terminateLatestUpdate() {
        return null;
    }

    @Override
    public String getLatestConfigFromDB(String clusterName) {
        LambdaQueryWrapper<ClusterPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ClusterPO::getName, clusterName);
        ClusterPO clusterPO = clusterDAO.selectOne(lambdaQueryWrapper);
        return clusterPO.getConfig();
    }

    @Override
    public String getLatestConfigFromCluster(String clusterName) throws IOException, ApiException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cluster cluster = clusterService.getClusterByName(clusterName);
        List<V1ConfigMap> v1ConfigMaps = kubernetesTool.listNamespacedConfigMap(cluster.getKubernetesCertificate(), cluster.getKubernetesNamespace());
        Map<String, Map<String, String>> configMap = v1ConfigMaps.stream()
                .filter(v1ConfigMap -> v1ConfigMap.getMetadata().getName().contains(DruidConstant.DRUID_CONFIG_KEYWORD))
                .collect(Collectors.toMap(
                        v1ConfigMap -> v1ConfigMap.getMetadata().getName(),
                        v1ConfigMap -> v1ConfigMap.getData().entrySet().stream()
                                .collect(Collectors.toMap(Map.Entry::getKey,
                                        entry -> filterConfigComment(entry.getValue())))
                ));
        return objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(configMap);
    }

    private String filterConfigComment(String config) {
        return Arrays.stream(config.split("\n"))
                .filter(line -> !line.trim().startsWith("#"))
                .collect(Collectors.joining("\n"));
    }
}
