package com.ran.storage.platform.persist.mysql.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.common.OperateLogPO;
import com.ran.storage.platform.common.bean.po.common.UserPO;
import org.springframework.stereotype.Repository;

/**
 * OperateLogDAO
 *
 * @author rwei
 * @since 2024/1/10 10:18
 */
@Repository
public interface OperateLogDAO extends BaseMapper<OperateLogPO> {
    int addAndSetId(OperateLogPO operateLogPO);
}
