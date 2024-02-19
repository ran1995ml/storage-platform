package com.ran.storage.platform.persist.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.ClusterPO;
import org.springframework.stereotype.Repository;

/**
 * ClusterDAO
 *
 * @author rwei
 * @since 2024/2/5 13:22
 */
@Repository
public interface ClusterDAO extends BaseMapper<ClusterPO> {
    Long addAndSetId(ClusterPO clusterPO);
}
