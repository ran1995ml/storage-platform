package com.ran.storage.platform.common.bean.dto.cluster;

import lombok.Data;

/**
 * DruidClusterAddDTO
 *
 * @author rwei
 * @since 2023/8/15 16:39
 */
@Data
public class DruidClusterAddDTO extends DruidClusterDTO {
    protected String name;

    protected String description;
}
