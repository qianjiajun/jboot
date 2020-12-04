package org.jpa.boot.entity;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class User.java
 */
@ToString
@Entity
@Table(name = "user")
public class User extends BaseModel {

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
    private Boolean sex;

    public User(){}

    /**
     * 新增用户使用
     *
     * @param createdBy 创建人id
     */
    public User(String code, String username, String telephone, String name, Integer age, Boolean sex, Long createdBy) {
        super(createdBy);
        this.password = username + "123456";
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public User(String code, String username, String telephone, String email, String qq, String wx, String name, String password, Integer age, Boolean sex, Long createdBy) {
        super(createdBy);
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
        super(id, updatedBy);
        this.password = password;
    }

    public User(Long id, String code, String username, String telephone, String email, String qq, String wx, String name, Integer age, Boolean sex, Long updatedBy) {
        super(id, updatedBy);
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

}
