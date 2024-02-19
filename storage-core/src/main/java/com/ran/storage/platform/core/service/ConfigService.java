package com.ran.storage.platform.core.service;

import io.kubernetes.client.openapi.ApiException;

import java.io.IOException;

/**
 * ConfigService
 *
 * @author rwei
 * @since 2024/2/5 23:00
 */
public interface ConfigService {

    Boolean updateConfig();

    Boolean terminateLatestUpdate();

    String getLatestConfigFromDB(String clusterName);

    String getLatestConfigFromCluster(String clusterName) throws IOException, ApiException;
}
