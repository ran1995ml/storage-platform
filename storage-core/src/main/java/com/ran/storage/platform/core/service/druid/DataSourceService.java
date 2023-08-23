package com.ran.storage.platform.core.service.druid;

import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;

import java.util.List;

/**
 * DataSourceService
 *
 * @author rwei
 * @since 2023/8/6 22:56
 */
public interface DataSourceService {
    List<String> listAllAvailableDataSource(DruidCluster druidCluster);

    List<String> getDimensionsAndMetricsForDataSource(DruidCluster druidCluster, String dataSourceName);

    List<String> getDimensionsForDataSource(DruidCluster druidCluster, String dataSourceName);

    List<String> getMetricsForDataSource(DruidCluster druidCluster, String dataSourceName);
}
