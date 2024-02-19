package com.ran.storage.platform.common.bean.vo;

import lombok.Data;

/**
 * ClusterVO
 *
 * @author rwei
 * @since 2023/11/24 16:04
 */
@Data
public class ClusterVO extends BaseTimeVO {
    protected Long id;

    protected String name;

    protected String description;
}
