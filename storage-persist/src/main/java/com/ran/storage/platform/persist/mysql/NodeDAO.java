package com.ran.storage.platform.persist.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.NodePO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NodeDAO
 *
 * @author rwei
 * @since 2024/2/18 17:35
 */
@Repository
public interface NodeDAO extends BaseMapper<NodePO> {
    Long batchInsert(List<NodePO> nodePOS);

    Long deleteByCluster(String clusterName);
}
