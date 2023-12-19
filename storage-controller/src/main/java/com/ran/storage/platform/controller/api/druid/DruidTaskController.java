package com.ran.storage.platform.controller.api.druid;

import com.ran.storage.platform.common.bean.dto.cluster.ClusterAddDTO;
import com.ran.storage.platform.common.bean.entity.druid.DruidTask;
import com.ran.storage.platform.common.bean.entity.result.Result;
import com.ran.storage.platform.common.bean.po.cluster.ClusterPO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.druid.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DruidTaskController
 *
 * @author rwei
 * @since 2023/11/27 21:07
 */
@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX)
public class DruidTaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "task")
    @ResponseBody
    public Result<List<DruidTask>> listAllTasks() {
        List<DruidTask> druidTasks = taskService.listAllTasks("http://druid-gcp.qe2.conviva.com:8888");
        return Result.buildSuccess(druidTasks);
    }
}
