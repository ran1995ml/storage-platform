package com.ran.storage.platform.common.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BasePO
 *
 * @author rwei
 * @since 2023/8/15 15:06
 */
@Data
public class BasePO implements Serializable {
    @TableId(type = IdType.AUTO)
    protected Long id;

    protected Date createTime;

    protected Date updateTime;
}
