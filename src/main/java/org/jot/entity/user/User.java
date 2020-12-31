package org.jot.entity.user;

import org.jot.entity.BaseEntity;
import org.jot.enumeration.Gender;
import org.jot.handler.convert.GenderEnumConvert;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Convert;
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

    @NonNull
    @Column(name = "code", columnDefinition = "varchar(36) not null default '' comment '工号/编码'")
    private String code;

    @NonNull
    @Column(name = "username", columnDefinition = "varchar(36) not null default '' comment '账号/用户名'")
    private String username;

    @NonNull
    @Column(name = "telephone", columnDefinition = "char(11) not null default '' comment '手机号'")
    private String telephone;

    @Column(name = "email", columnDefinition = "varchar(100) default '' comment '电子邮箱'")
    private String email;

    @Column(name = "qq", columnDefinition = "varchar(12) default '' comment 'QQ号'")
    private String qq;

    @Column(name = "wx", columnDefinition = "varchar(20) default '' comment '微信号'")
    private String wx;

    @NonNull
    @Column(name = "name", columnDefinition = "varchar(60) not null default '' comment '姓名'")
    private String name;

    @NonNull
    @Column(name = "age", length = 3, columnDefinition = "tinyint(3) not null default 0 comment '年龄'")
    private Integer age;

    @NonNull
    @Convert(converter = GenderEnumConvert.class)
    @Column(name = "gender", nullable = false, columnDefinition = "tinyint(1) not null default -1 comment '性别'")
    private Gender gender = Gender.UNKNOWN;

    public User() {
    }

    /**
     * 新增用户使用
     *
     * @param createdBy 创建人id
     */
    public User(String code, String username, String telephone, String name, Integer age, Gender gender, Long createdBy) {
        this.setCreatedBy(createdBy);
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User(String code, String username, String telephone, String email, String qq, String wx, String name, Integer age, Gender gender, Long createdBy) {
        this.setCreatedBy(createdBy);
        this.code = code;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.qq = qq;
        this.wx = wx;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * 修改密码使用
     *
     * @param id        id
     * @param updatedBy 创建人id
     */
    public User(Long id, Long updatedBy) {
        this.setId(id);
        this.setUpdatedBy(updatedBy);
    }

    public User(Long id, String code, String username, String telephone, String email, String qq, String wx, String name, Integer age, Gender gender, Long updatedBy) {
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
        this.gender = gender;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
