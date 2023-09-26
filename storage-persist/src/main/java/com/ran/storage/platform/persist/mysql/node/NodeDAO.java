package com.ran.storage.platform.persist.mysql.node;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.node.NodePO;
import org.springframework.stereotype.Repository;

/**
 * NodeDAO
 *
 * @author rwei
 * @since 2023/9/3 17:34
 */
@Repository
public interface NodeDAO extends BaseMapper<NodePO> {
    void addAndSetId(NodePO nodePO);
}
