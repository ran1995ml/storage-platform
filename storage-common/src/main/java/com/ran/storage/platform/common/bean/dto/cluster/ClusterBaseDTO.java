package com.ran.storage.platform.common.bean.dto.cluster;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import lombok.Data;

/**
 * ClusterDTO
 *
 * @author rwei
 * @since 2023/11/24 15:10
 */
@Data
public class ClusterBaseDTO extends BaseDTO {
    protected String version;
}
