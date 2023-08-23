package com.ran.storage.platform.core.service.druid.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.core.service.druid.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * DataSourceServiceImpl
 *
 * @author rwei
 * @since 2023/8/7 14:27
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);

    @Autowired
    private RestTool restTool;

    private final Cache<String, List<String>> dataSourcesCache = Caffeine.newBuilder()
            .expireAfterWrite(90, TimeUnit.SECONDS)
            .maximumSize(200)
            .build();

    @Override
    public List<String> listAllAvailableDataSource(DruidCluster druidCluster) {
        String path = String.format("%s/druid/v2/datasources", druidCluster.getRouter());
        return restTool.getForObject(path, new HashMap<>(), new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getDimensionsAndMetricsForDataSource(DruidCluster druidCluster, String dataSourceName) {
        String path = String.format("%s/druid/v2/datasources/%s", druidCluster.getRouter(), dataSourceName);
        return restTool.getForObject(path, new HashMap<>(), new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getDimensionsForDataSource(DruidCluster druidCluster, String dataSourceName) {
        String path = String.format("%s/druid/v2/datasources/%s/dimensions", druidCluster.getRouter(), dataSourceName);
        return restTool.getForObject(path, new HashMap<>(), new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getMetricsForDataSource(DruidCluster druidCluster, String dataSourceName) {
        String path = String.format("%s/druid/v2/datasources/%s/metrics", druidCluster.getRouter(), dataSourceName);
        return restTool.getForObject(path, new HashMap<>(), new TypeReference<List<String>>() {});
    }
}
