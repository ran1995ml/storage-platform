package com.ran.storage.platform.common.bean.vo.cluster;

import com.ran.storage.platform.common.bean.vo.BaseTimeVO;
import lombok.Data;

/**
 * ClusterBaseVO
 *
 * @author rwei
 * @since 2023/11/24 16:04
 */
@Data
public class ClusterBaseVO extends BaseTimeVO {
    protected Long id;

    protected String name;

    protected String type;

    protected String description;
}
