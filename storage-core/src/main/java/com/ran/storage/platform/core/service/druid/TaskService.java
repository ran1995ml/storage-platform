package com.ran.storage.platform.core.service.druid;

import com.ran.storage.platform.common.bean.entity.druid.DruidTask;

import java.util.List;

/**
 * TaskService
 *
 * @author rwei
 * @since 2023/11/24 16:23
 */
public interface TaskService {
    List<DruidTask> listAllTasks(String router);

    List<DruidTask> listRunningTasks(String router);
}
