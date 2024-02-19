package com.ran.storage.platform.core.service;

import com.ran.storage.platform.common.bean.dto.UserLoginDTO;
import com.ran.storage.platform.common.bean.entity.User;
import com.ran.storage.platform.common.exception.LoginSecurityException;
import com.ran.storage.platform.common.exception.NotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserService
 *
 * @author rwei
 * @since 2023/12/26 13:25
 */
public interface UserService {
    List<User> getAllUsers();

    User getUserByName(String username) throws NotExistException;

    Long register(User user);

    String login(UserLoginDTO userLoginDTO, HttpServletRequest request) throws NotExistException, LoginSecurityException;

    void updateRole(User user) throws NotExistException;
}
