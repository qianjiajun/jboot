package org.jot.entity.user;

import org.jot.annotation.ToString;
import org.jot.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class User.java
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

    @ToString.Exclude
    private final static String DEFAULT_PASSWORD = "123456";

    @Column(name = "code", length = 36)
    private String code;

    @Column(name = "username", nullable = false, length = 36)
    private String username;

    @Column(name = "telephone", nullable = false, length = 11)
    private String telephone;

    @ToString.Exclude
    @Column(name = "password", nullable = false, length = 36)
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "qq", length = 20)
    private String qq;

    @Column(name = "wx", length = 20)
    private String wx;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "age", length = 3)
    private Integer age;

    @Column(name = "sex", nullable = false)
    private boolean sex;

    public User() {
    }

    /**
     * 新增用户使用
     *
     * @param createdBy 创建人id
     */
    public User(String code, String username, String telephone, String name, Integer age, Boolean sex, Long createdBy) {
        this.setCreatedBy(createdBy);
        this.resetPassword();
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(String code, String username, String telephone, String email, String qq, String wx, String name, String password, Integer age, Boolean sex, Long createdBy) {
        this.setCreatedBy(createdBy);
        this.password = password;
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.qq = qq;
        this.wx = wx;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * 修改密码使用
     *
     * @param id        id
     * @param password  password
     * @param updatedBy 创建人id
     */
    public User(Long id, String password, Long updatedBy) {
        this.setId(id);
        this.setUpdatedBy(updatedBy);
        this.password = password;
    }

    public User(Long id, String code, String username, String telephone, String email, String qq, String wx, String name, Integer age, Boolean sex, Long updatedBy) {
        this.setId(id);
        this.setUpdatedBy(updatedBy);
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.qq = qq;
        this.wx = wx;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void resetPassword() {
        this.password = this.username == null ? DEFAULT_PASSWORD : this.username.replace(" ", "") + DEFAULT_PASSWORD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
