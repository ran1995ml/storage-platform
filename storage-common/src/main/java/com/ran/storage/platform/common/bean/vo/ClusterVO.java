package com.ran.storage.platform.common.bean.vo;

import lombok.Data;

/**
 * ClusterVO
 *
 * @author rwei
 * @since 2023/8/26 22:35
 */
@Data
public class ClusterVO extends BaseVO {
    protected String name;

    protected String type;

    protected String description;
}
