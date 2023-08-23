package com.ran.storage.platform.controller.api.druid;

import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.core.service.druid.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DataSourceController
 *
 * @author rwei
 * @since 2023/8/7 22:06
 */
@RestController
public class DataSourceController {
    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping(value = "/druid/v1/datasourcs")
    public Result<List<String>> listAllAvailableDataSource() {
        DruidCluster druidCluster = new DruidCluster();
        List<String> dataSourceList = dataSourceService.listAllAvailableDataSource(druidCluster);
        return Result.buildSuccess(dataSourceList);
    }

    @GetMapping(value = "/druid/v1/metrics")
    public Result<List<String>> getMetricsForDataSource() {
        DruidCluster druidCluster = new DruidCluster();
        List<String> metricsList = dataSourceService.getMetricsForDataSource(druidCluster, "druid_ingestion");
        return Result.buildSuccess(metricsList);
    }

    @GetMapping(value = "/druid/v1/{datasource}/demensions")
    public Result<List<String>> getDimensionsForDataSource(@PathVariable String datasource) {
        DruidCluster druidCluster = new DruidCluster();
        List<String> dimensionsList = dataSourceService.getDimensionsForDataSource(druidCluster, datasource);
        return Result.buildSuccess(dimensionsList);
    }

}
