package com.ran.storage.platform.common.bean.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * BaseTimeVO
 *
 * @author rwei
 * @since 2023/11/24 16:02
 */
@Data
@ToString
public class BaseTimeVO extends BaseVO {
    private Date createTime;

    private Date updateTime;
}
