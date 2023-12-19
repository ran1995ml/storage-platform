package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BasePO
 *
 * @author rwei
 * @since 2023/11/24 11:12
 */
@Data
public class BasePO implements Serializable {
    protected Long id;

    protected Date createTime;

    protected Date updateTime;
}
