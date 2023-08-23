package com.ran.storage.platform.common.bean.dto.cluster;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import lombok.Data;

/**
 * ClusterDTO
 *
 * @author rwei
 * @since 2023/8/15 16:29
 */
@Data
public class ClusterDTO extends BaseDTO {
    protected String name;

    protected String description;
}
