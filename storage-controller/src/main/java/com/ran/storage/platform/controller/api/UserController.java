package com.ran.storage.platform.controller.api;

import com.ran.storage.platform.common.annotation.OperateLog;
import com.ran.storage.platform.common.bean.dto.UserLoginDTO;
import com.ran.storage.platform.common.bean.dto.UserRegisterDTO;
import com.ran.storage.platform.common.bean.entity.User;
import com.ran.storage.platform.common.bean.entity.Result;
import com.ran.storage.platform.common.bean.vo.UserDisplayVO;
import com.ran.storage.platform.common.bean.vo.UserLoginVO;
import com.ran.storage.platform.common.bean.vo.UserUpdateRoleDTO;
import com.ran.storage.platform.common.constant.ApiPrefix;
import com.ran.storage.platform.common.exception.NotExistException;
import com.ran.storage.platform.common.utils.ConvertUtils;
import com.ran.storage.platform.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * UserController
 *
 * @author rwei
 * @since 2023/12/26 14:32
 */
@RestController
@RequestMapping(ApiPrefix.API_V1_PREFIX)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{username}/basic")
    @ResponseBody
    @OperateLog(module = "user", type = "get", desc = "get user info")
    public Result<UserDisplayVO> getUserByName(@PathVariable String username) {
        try {
            User user = userService.getUserByName(username);
            return Result.buildSuccess(ConvertUtils.obj2Obj(user, UserDisplayVO.class));
        } catch (Exception e) {
            return Result.buildFailure();
        }
    }

    @PostMapping(value = "/user/register")
    @ResponseBody
    public Result<Long> register(@RequestBody UserRegisterDTO registerDTO) {
        User user = ConvertUtils.obj2Obj(registerDTO, User.class);
        return Result.buildSuccess(userService.register(user));
    }

    @PostMapping(value = "/user/login")
    @ResponseBody
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        try {
            String token = userService.login(userLoginDTO, request);
            UserLoginVO userLoginVO = ConvertUtils.obj2Obj(userLoginDTO, UserLoginVO.class);
            userLoginVO.setToken(token);
            return Result.buildSuccess(userLoginVO);
        } catch (Exception e) {
            return Result.buildFailure();
        }
    }

    @PostMapping(value = "/user/role")
    @ResponseBody
    public Result<Void> updateRole(@RequestBody UserUpdateRoleDTO userUpdateRoleDTO) {
        try {
            userService.updateRole(ConvertUtils.obj2Obj(userUpdateRoleDTO, User.class));
            return Result.buildSuccess();
        } catch (NotExistException e) {
            return Result.buildFailure();
        }
    }
}
