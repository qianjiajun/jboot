package org.jot.repository.user;

import org.jot.entity.user.User;
import org.jot.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class UserRepository.java
 */
public interface UserRepository extends BaseRepository<User> {

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
    @Query(value = "select u.* from user u left join secret p on u.id = p.user_id where p.password=:password and u.id =:userId", nativeQuery = true)
    Optional<User> findByIdAndPassword(@Param("userId") Long id, @Param("password") String password);

    /**
     * 根据 username 和 password 查询用户
     *
     * @param username 用户名
     * @param password 用户密码
     * @return User
     */
    @Query(value = "select u.* from user u left join secret p on u.id = p.user_id where p.password=:password and u.username=:username", nativeQuery = true)
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据 username 和 password 查询用户
     *
     * @param username 用户名
     * @return User
     */
    @Query("from User u where u.username=:username")
    User findUser(@Param("username") String username);

}
