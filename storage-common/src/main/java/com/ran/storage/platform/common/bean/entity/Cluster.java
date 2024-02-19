package com.ran.storage.platform.common.bean.entity;

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
    protected Long id;

    protected String name;

    protected String description;

    protected String image;

    protected String config;

    protected String kubernetesCertificate;

    protected String kubernetesNamespace;

    protected Date createTime;

    protected Date updateTime;
}
