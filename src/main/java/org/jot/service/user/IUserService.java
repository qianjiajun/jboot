package org.jot.service.user;

import org.jot.entity.user.Secret;
import org.jot.entity.user.User;
import org.jot.service.base.IBaseService;
import org.jot.util.GlobalException;

import java.util.Optional;

/**
 * @Author qjj
 * @Date 2020-12-22 11:40
 * @Version 1.0
 * @Class UserService.java
 */
public interface IUserService extends IBaseService<User> {


    /**
     * 新增用户
     *
     * @param user
     * @param secret
     */
    User addUser(User user, Secret secret) throws GlobalException;

    /**
     * 重置密码
     *
     * @param secret
     */
    void resetPassword(Secret secret);


    /**
     * 根据 code 查询用户
     *
     * @param code 用户编码
     * @return User
     */
    Optional<User> findByCode(String code);

    /**
     * 根据 username 查询用户
     *
     * @param username 用户名
     * @return User
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据 telephone 查询用户
     *
     * @param telephone 手机号
     * @return User
     */
    Optional<User> findByTelephone(String telephone);

    /**
     * 根据 email 查询用户
     *
     * @param email 电子邮箱
     * @return User
     */
    Optional<User> findByEmail(String email);

    /**
     * 根据 qq 查询用户
     *
     * @param qq qq号
     * @return User
     */
    Optional<User> findByQq(String qq);

    /**
     * 根据 wx 查询用户
     *
     * @param wx 微信
     * @return User
     */
    Optional<User> findByWx(String wx);

    /**
     * 根据 id 和 password 查询用户
     *
     * @param id       用户id
     * @param password 用户密码
     * @return User
     */
    Optional<User> findByIdAndPassword(Long id, String password);

    /**
     * 根据 username 和 password 查询用户
     *
     * @param username 用户名
     * @param password 用户密码
     * @return User
     */
    Optional<User> findByUsernameAndPassword(String username, String password);

    /**
     * 根据 username 和 password 查询用户
     *
     * @param username 用户名
     * @return User
     */
    User findUser(String username);

}
