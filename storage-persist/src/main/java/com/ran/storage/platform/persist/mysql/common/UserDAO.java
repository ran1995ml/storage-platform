package com.ran.storage.platform.persist.mysql.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ran.storage.platform.common.bean.po.common.UserPO;
import org.springframework.stereotype.Repository;

/**
 * UserDAO
 *
 * @author rwei
 * @since 2023/12/26 13:44
 */
@Repository
public interface UserDAO extends BaseMapper<UserPO> {
    int addAndSetId(UserPO userPO);
}
