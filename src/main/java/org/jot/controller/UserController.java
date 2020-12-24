package org.jot.controller;

import org.jot.annotation.Log;
import org.jot.annotation.Verification;
import org.jot.entity.user.User;
import org.jot.service.user.IUserService;
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
public class UserController {

    @Autowired
    private IUserService userService;

    @Verification(required = false)
    @Log(value = "分页查询用户")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Page<User> pageUser(@RequestParam(value = "page", defaultValue = "1") int page, String username) {
        Specification<User> userSpecification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (username != null && !username.isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Order.desc("id")));
        Page<User> userPage = userService.findPage(userSpecification, pageable);
        return userPage;
    }

}
