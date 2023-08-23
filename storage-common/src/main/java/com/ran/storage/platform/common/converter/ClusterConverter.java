package com.ran.storage.platform.common.converter;

import com.ran.storage.platform.common.bean.dto.cluster.DruidClusterDTO;
import com.ran.storage.platform.common.bean.po.cluster.DruidClusterPO;
import com.ran.storage.platform.common.utils.ConvertUtil;

/**
 * ClusterConverter
 *
 * @author rwei
 * @since 2023/8/15 16:58
 */
public class ClusterConverter {
    private ClusterConverter() {}

    public static DruidClusterPO convert2DruidClusterPO(DruidClusterDTO druidClusterDTO) {
        DruidClusterPO druidClusterPO = ConvertUtil.obj2Obj(druidClusterDTO, DruidClusterPO.class);
        return druidClusterPO;
    }
}
