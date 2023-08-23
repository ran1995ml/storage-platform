package com.ran.storage.platform.common.bean.entity.cluster;

import lombok.Data;

import java.util.Date;

/**
 * Cluster
 *
 * @author rwei
 * @since 2023/8/2 17:44
 */
@Data
public class Cluster {
    protected Long id;

    protected String name;

    protected String description;

    protected String version;

    protected Integer runState;

    protected Date createTime;

    protected Date updateTime;
}
