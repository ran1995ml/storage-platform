package com.ran.storage.platform.core.service.druid.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ran.storage.platform.common.bean.entity.druid.DruidTask;
import com.ran.storage.platform.common.component.RestTool;
import com.ran.storage.platform.common.constant.DruidConstant;
import com.ran.storage.platform.core.service.druid.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * DruidTaskService
 *
 * @author rwei
 * @since 2023/11/27 20:59
 */
@Service
public class DruidTaskService implements TaskService {
    @Autowired
    private RestTool restTool;

    @Override
    public List<DruidTask> listAllTasks(String router) {
        String url = String.format("%s%s", router, DruidConstant.DRUID_TASK_API);
        return restTool.getForObject(url, new HashMap<>(), new TypeReference<List<DruidTask>>() {});
    }

    @Override
    public List<DruidTask> listRunningTasks(String router) {
        return null;
    }


}
