package org.jot.service.user.impl;

import org.jot.entity.user.User;
import org.jot.repository.user.UserRepository;
import org.jot.service.base.impl.BaseServiceImpl;
import org.jot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Optional<User> findByCode(String code) {
        return userRepository.findByCode(code);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByTelephone(String telephone) {
        return userRepository.findByTelephone(telephone);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByQq(String qq) {
        return userRepository.findByQq(qq);
    }

    @Override
    public Optional<User> findByWx(String wx) {
        return userRepository.findByWx(wx);
    }

    @Override
    public Optional<User> findByIdAndPassword(Long id, String password) {
        return userRepository.findByIdAndPassword(id, password);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findUser(String username) {
        return userRepository.findUser(username);
    }
}
