package org.jot.controller.admin;

import org.jot.annotation.Log;
import org.jot.annotation.Verification;
import org.jot.controller.BaseController;
import org.jot.entity.user.User;
import org.jot.enumeration.StatusCode;
import org.jot.service.user.IUserService;
import org.jot.util.Const;
import org.jot.util.ResultSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author qjj
 * @Date 2020-12-22 15:37
 * @Version 1.0
 * @Class UserController.java
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private IUserService userService;

    @Verification(required = false)
    @Log(value = "登录", isRecordParameters = true, isRecordResultData = true)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet login(@RequestParam(value = "username") String username,
                                            @RequestParam(value = "password") String password) {
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        if (!user.isPresent()) {
            return ResultSetBuilder.fail(StatusCode.USER_NOT_EXIST_OR_PASSWORD_ERROR);
        }
        session.setAttribute(Const.SESSION_IS_LOGIN, true);
        User u = user.get();
        session.setAttribute(Const.SESSION_USER, u);
        session.setAttribute(Const.SESSION_USER_ID, u.getId());
        return ResultSetBuilder.success().setResult(u.exclude());

    }

    @Verification()
    @Log(value = "登出", isRecordParameters = true, isRecordResultData = true)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet logout() {
        Boolean isLogin = (Boolean) session.getAttribute(Const.SESSION_IS_LOGIN);
        if (isLogin == null || isLogin == false) {
            return ResultSetBuilder.fail(StatusCode.NON_LOGIN);
        }
        session.invalidate();
        session = request.getSession(true);
        return ResultSetBuilder.success();

    }

}
