package org.jot.entity.user;

import org.jot.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "secret")
public class Secret extends BaseEntity {

    private final static String DEFAULT_PASSWORD = "123456";

    @Column(name = "user_id", columnDefinition = "bigint not null default 0 comment '用户id'")
    private Long userId;

    @Column(name = "old_password", columnDefinition = "varchar(36) not null default '' comment '原密码'")
    private String oldPassword;

    @Column(name = "password", columnDefinition = "varchar(36) not null default '' comment '新密码'")
    private String password;


    public Secret() {
        this.oldPassword = DEFAULT_PASSWORD;
        this.password = DEFAULT_PASSWORD;
    }

    public Secret(Long createdBy) {
        this();
        this.setCreatedBy(createdBy);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void resetPassword() {
        this.password = DEFAULT_PASSWORD;
    }


}
