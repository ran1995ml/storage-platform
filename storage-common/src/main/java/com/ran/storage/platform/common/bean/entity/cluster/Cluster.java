package com.ran.storage.platform.common.bean.entity.cluster;

import lombok.Data;

import java.util.Date;

/**
 * Cluster
 *
 * @author rwei
 * @since 2023/11/23 17:36
 */
@Data
public class Cluster {
    private Long id;

    private String type;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;
}
