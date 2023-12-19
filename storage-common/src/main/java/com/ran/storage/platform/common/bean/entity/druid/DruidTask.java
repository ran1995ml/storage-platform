package com.ran.storage.platform.common.bean.entity.druid;

import com.ran.storage.platform.common.bean.entity.common.Location;
import lombok.Data;

import java.util.Date;

/**
 * DruidTask
 *
 * @author rwei
 * @since 2023/11/27 13:48
 */
@Data
public class DruidTask {
    private String id;

    private String groupId;

    private String type;

    private Date createdTime;
    
    private Date queueInsertionTime;
    
    private String statusCode;

    private String status;

    private String runnerStatusCode;

    private Long duration;

    private Location location;

    private String dataSource;

    private String errorMsg;
}
