package com.ran.storage.platform.core.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ran.storage.platform.common.bean.dto.common.UserLoginDTO;
import com.ran.storage.platform.common.bean.entity.common.User;
import com.ran.storage.platform.common.bean.po.common.UserPO;
import com.ran.storage.platform.common.constant.UserConstant;
import com.ran.storage.platform.common.exception.LoginSecurityException;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.common.utils.TokenUtils;
import com.ran.storage.platform.core.service.common.UserService;
import com.ran.storage.platform.persist.mysql.common.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * UserServiceImpl
 *
 * @author rwei
 * @since 2023/12/26 13:43
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        List<UserPO> userPOList = userDAO.selectList(Wrappers.emptyWrapper());
        return ConvertUtils.list2List(userPOList, User.class);
    }

    @Override
    public User getUserByName(String username) throws NotExistException {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UserConstant.USERNAME_COLUMN, username);
        List<UserPO> userPOList = userDAO.selectList(queryWrapper);
        if (userPOList.isEmpty()) {
            throw new NotExistException(String.format("Non-existent user %s", username));
        }
        return ConvertUtils.obj2Obj(userPOList.get(0), User.class);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO, HttpServletRequest request) throws NotExistException, LoginSecurityException {
        User user = getUserByName(userLoginDTO.getUsername());
        if (Objects.isNull(user)) {
            throw new NotExistException(String.format("Non-existent user %s", userLoginDTO.getUsername()));
        }

        String md5Password = encrypt(userLoginDTO.getPassword());
        if (!user.getPassword().equals(md5Password)) {
            throw new LoginSecurityException("Wrong password");
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(UserConstant.USER_SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute(UserConstant.USER_LOGIN_SESSION_KEY, userLoginDTO.getUsername());
        logger.info("User {} login successfully", userLoginDTO.getUsername());
        return TokenUtils.sign(user);
    }

    @Override
    public void updateRole(User user) throws NotExistException {
        Long userId = user.getId();
        String role = user.getRole();
        UpdateWrapper<UserPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(UserConstant.USERID_COLUMN, userId);
        UserPO newUserPO = new UserPO();
        newUserPO.setRole(role);
        logger.info("user id: {}", userId);
        int affectRow = userDAO.update(newUserPO, updateWrapper);
        if (affectRow <= 0) {
            throw new NotExistException(String.format("Non-existent user %s", user.getUsername()));
        }
        logger.info("Assign role {} for user {}", role, user.getUsername());
    }

    @Override
    public Long register(User user) {
        user.setPassword(encrypt(user.getPassword()));
        user.setRole(UserConstant.DEFAULT_USER_ROLE_NAME);
        UserPO userPO = ConvertUtils.obj2Obj(user, UserPO.class);
        userDAO.addAndSetId(userPO);
        return userPO.getId();
    }

    private String encrypt(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
