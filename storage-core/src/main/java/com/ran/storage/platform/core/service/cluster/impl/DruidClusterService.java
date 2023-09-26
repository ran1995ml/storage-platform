package com.ran.storage.platform.core.service.cluster.impl;

import com.ran.storage.platform.common.component.RestTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DruidClusterService
 *
 * @author rwei
 * @since 2023/9/4 23:04
 */
@Service("druidClusterService")
public class DruidClusterService {
    @Autowired
    private RestTool restTool;

}
