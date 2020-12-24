package org.jot.controller.user;

import org.jot.annotation.Log;
import org.jot.annotation.Verification;
import org.jot.controller.BaseController;
import org.jot.entity.user.User;
import org.jot.service.user.IUserService;
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
            if (username != null && !username.isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
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

}
