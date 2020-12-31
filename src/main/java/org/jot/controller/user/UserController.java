package org.jot.controller.user;

import org.apache.commons.lang3.StringUtils;
import org.jot.annotation.Log;
import org.jot.annotation.Verification;
import org.jot.controller.BaseController;
import org.jot.entity.user.Secret;
import org.jot.entity.user.User;
import org.jot.enumeration.Status;
import org.jot.service.user.IUserService;
import org.jot.util.GlobalException;
import org.jot.util.ResultSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qjj
 * @Date 2020-12-22 15:37
 * @Version 1.0
 * @Class UserController.java
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Verification
    @Log(value = "分页查询用户")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet pageUser(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                               @RequestParam(value = "username") String username) {
        Specification<User> userSpecification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("status"), Status.ACTIVE));
            if (StringUtils.isNotBlank(username)) {
                predicateList.add(criteriaBuilder.like(root.get("username"), String.format("%%%s%%", username)));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<User> page = userService.findPage(userSpecification, pageable);
        return ResultSetBuilder.success().setResult(page);
    }

    @Verification
    @Log(value = "根据id查询用户", isRecordParameters = true, isRecordResultData = true)
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet findUserById(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        return ResultSetBuilder.success().setResult(user);
    }

    @Verification
    @Log(value = "新增用户", isRecordParameters = true, isRecordResultData = true)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet addUser(@RequestBody User user, String password) throws GlobalException {
        user.setCreatedBy(operatorId);
        Secret secret = new Secret(operatorId);
        if (StringUtils.isNotBlank(password)) {
            secret.setPassword(password);

        }
        User u = userService.addUser(user, secret);
        return ResultSetBuilder.success().setResult(u.getId());
    }

    @Verification
    @Log(value = "删除用户", isRecordParameters = true, isRecordResultData = true)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultSetBuilder.ResultSet deleteById(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        user.setUpdatedBy(operatorId);
        user.setStatus(Status.DELETED);
        User u = userService.edit(user);
        return ResultSetBuilder.success().setResult(u.getId());
    }

}
