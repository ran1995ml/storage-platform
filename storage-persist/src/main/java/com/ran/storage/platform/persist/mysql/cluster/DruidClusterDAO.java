package com.ran.storage.platform.persist.mysql.cluster;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.cluster.DruidClusterPO;
import org.springframework.stereotype.Repository;

/**
 * DruidClusterDAO
 *
 * @author rwei
 * @since 2023/8/23 14:19
 */
@Repository
public interface DruidClusterDAO extends BaseMapper<DruidClusterPO> {
    Long addAndSetId(DruidClusterPO druidClusterPO);
}
