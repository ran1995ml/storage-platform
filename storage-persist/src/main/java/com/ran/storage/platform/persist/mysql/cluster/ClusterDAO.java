package com.ran.storage.platform.persist.mysql.cluster;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;

/**
 * ClusterDAO
 *
 * @author rwei
 * @since 2023/9/3 17:32
 */
@Repository
public interface ClusterDAO extends BaseMapper<ClusterPO> {
    void addAndSetId(ClusterPO clusterPO);
}
