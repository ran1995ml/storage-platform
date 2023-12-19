package com.ran.storage.platform.persist.mysql.cluster;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import org.springframework.stereotype.Repository;

/**
 * ClusterDAO
 *
 * @author rwei
 * @since 2023/11/24 14:32
 */
@Repository
public interface ClusterDAO extends BaseMapper<ClusterPO> {
    int addAndSetId(ClusterPO clusterPO);
}
