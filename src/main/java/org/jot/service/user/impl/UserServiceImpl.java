package org.jot.service.user.impl;

import org.apache.commons.lang3.StringUtils;
import org.jot.entity.user.Secret;
import org.jot.entity.user.User;
import org.jot.enumeration.Status;
import org.jot.enumeration.StatusCode;
import org.jot.repository.password.PasswordRepository;
import org.jot.repository.user.UserRepository;
import org.jot.service.base.impl.BaseServiceImpl;
import org.jot.service.user.IUserService;
import org.jot.util.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author qjj
 * @Date 2020-12-22 11:41
 * @Version 1.0
 * @Class UserServiceImpl.java
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordRepository passwordRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User addUser(User user, Secret secret) throws GlobalException {

        long count = userRepository.count((Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("code"), user.getCode()),
                    criteriaBuilder.equal(root.get("username"), user.getUsername()),
                    criteriaBuilder.equal(root.get("telephone"), user.getTelephone())
            ));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
        if (count >= 1) {
            throw new GlobalException(StatusCode.USER_EXISTS);
        }
        if (secret == null) {
            secret = new Secret();
        }
        User u = userRepository.save(user);
        if (secret.getUserId() == null) {
            secret.setUserId(u.getId());
        }
        secret.setCreatedBy(user.getCreatedBy());
        passwordRepository.save(secret);
        return u;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resetPassword(Secret secret) {
        secret.resetPassword();
        passwordRepository.save(secret);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByCode(String code) {
        return userRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByTelephone(String telephone) {
        return userRepository.findByTelephone(telephone);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByQq(String qq) {
        return userRepository.findByQq(qq);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByWx(String wx) {
        return userRepository.findByWx(wx);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByIdAndPassword(Long id, String password) {
        return userRepository.findByIdAndPassword(id, password);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUser(String username) {
        return userRepository.findUser(username);
    }
}
