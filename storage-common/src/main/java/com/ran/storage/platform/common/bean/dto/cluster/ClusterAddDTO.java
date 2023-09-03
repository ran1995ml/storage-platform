package com.ran.storage.platform.common.bean.dto.cluster;

import com.ran.storage.platform.common.bean.dto.BaseDTO;
import com.ran.storage.platform.common.bean.entity.node.Node;
import lombok.Data;

import java.util.List;

/**
 * ClusterAddDTO
 *
 * @author rwei
 * @since 2023/8/27 17:04
 */
@Data
public class ClusterAddDTO extends BaseDTO {
    private String name;

    private String description;

    private List<Node> nodes;

    private String type;
}
