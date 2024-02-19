package com.ran.storage.platform.common.bean.entity;

import lombok.Data;

import java.util.Date;

/**
 * Node
 *
 * @author rwei
 * @since 2024/2/15 20:37
 */
@Data
public class Node {
    protected String nodeName;

    protected String clusterName;

    protected String ip;

    protected String hostname;

    protected String status;

    protected String nodeType;

    protected Date startTime;

    protected int failureCount;

    protected int consecutiveFailureCount = 0;

    protected int consecutiveSuccessCount = 0;

    protected boolean isReady;

    protected boolean isHealthy;
}
