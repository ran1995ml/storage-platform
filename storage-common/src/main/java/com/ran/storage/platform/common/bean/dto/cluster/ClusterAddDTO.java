package com.ran.storage.platform.common.bean.dto.cluster;

import lombok.Data;

/**
 * ClusterAddDTO
 *
 * @author rwei
 * @since 2023/11/24 15:15
 */
@Data
public class ClusterAddDTO extends ClusterBaseDTO {
    protected String name;

    protected String type;

    protected String description;
}
