package com.ran.storage.platform.core.service.cluster.impl;

import com.ran.storage.platform.common.bean.entity.cluster.DruidCluster;
import com.ran.storage.platform.common.bean.po.cluster.DruidClusterPO;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.common.utils.ConvertUtil;
import com.ran.storage.platform.core.service.cluster.ClusterService;
import com.ran.storage.platform.persist.mysql.cluster.DruidClusterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DruidClusterServiceImpl
 *
 * @author rwei
 * @since 2023/8/22 16:40
 */
@Service
public class DruidClusterServiceImpl implements ClusterService<DruidCluster> {
    private static final Logger logger = LoggerFactory.getLogger(RestTool.class);

    @Autowired
    private DruidClusterDAO druidClusterDAO;

    @Override
    public List<DruidCluster> listAllClusters() {
        List<DruidClusterPO> clusters = druidClusterDAO.selectList(null);
        return ConvertUtil.list2List(clusters, DruidCluster.class);
    }

    @Override
    public Long addCluster(DruidCluster cluster) {
        DruidClusterPO druidClusterPO = ConvertUtil.obj2Obj(cluster, DruidClusterPO.class);
        druidClusterDAO.addAndSetId(druidClusterPO);
        return druidClusterPO.getId();
    }

    @Override
    public DruidCluster getClusterById(Long clusterId) {
        return ConvertUtil.obj2Obj(druidClusterDAO.selectById(clusterId), DruidCluster.class);
    }

    @Override
    public void updateCluster(DruidCluster cluster) {
        try {
            logger.info("druid cluster {}", cluster.getId());
            int affectRow = druidClusterDAO.updateById(ConvertUtil.obj2Obj(cluster, DruidClusterPO.class));
            System.out.println(affectRow);
            if (affectRow <= 0) {
                throw new Exception("error");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCluster(Long clusterId) {
        druidClusterDAO.deleteById(clusterId);
    }
}
