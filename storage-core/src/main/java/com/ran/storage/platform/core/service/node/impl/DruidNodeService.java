package com.ran.storage.platform.core.service.node.impl;

import com.ran.storage.platform.common.bean.po.node.NodePO;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.persist.mysql.node.NodeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * DruidNodeService
 *
 * @author rwei
 * @since 2023/9/4 22:11
 */
@Service("druidNodeService")
public class DruidNodeService extends BaseNodeService {
    private static final Logger logger = LoggerFactory.getLogger(DruidNodeService.class);

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private RestTool restTool;

    @Autowired
    public DruidNodeService(NodeDAO nodeDAO) {
        super(nodeDAO);
    }

    @Override
    public Boolean getNodeRunState(Long nodeId) {
        NodePO nodePO = nodeDAO.selectById(nodeId);
        String host = nodePO.getHost();
        Integer port = nodePO.getPort();
        String path = String.format("http://%s:%s/status/health", host, port);
        logger.info("Sending get request to url {}", path);
        return restTool.getForObject(path, new HashMap<>(), Boolean.class);
    }
}
