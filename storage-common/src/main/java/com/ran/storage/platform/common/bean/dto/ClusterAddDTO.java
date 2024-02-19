package com.ran.storage.platform.common.bean.dto;

import lombok.Data;

/**
 * ClusterAddDTO
 *
 * @author rwei
 * @since 2023/11/24 15:15
 */
@Data
public class ClusterAddDTO {
    protected String name;

    protected String description;

    protected String kubernetesNamespace;

    protected String kubernetesCertificate;
}
