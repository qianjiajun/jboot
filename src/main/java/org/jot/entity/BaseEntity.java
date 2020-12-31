package org.jot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jot.annotation.ToString;
import org.jot.enumeration.Status;
import org.jot.handler.convert.StatusEnumConvert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @Author qjj
 * @Date 2020-12-04 10:35
 * @Version 1.0
 * @Class BaseModel2.java
 * JOINED：继承的子类各自生成一张表
 * SINGLE_TABLE：只生成一张表，用一个字段当鉴别器
 * TABLE_PER_CLASS：每个类都生产一张表，包括基类
 * 启动创建时间 CreatedDate 和 最后修改时间 LastModifiedDate 自动更新,
 * 需要监听AuditingEntityListener
 * 并且在启动类添加 @EnableJpaAuditing 注解
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Convert(converter = StatusEnumConvert.class)
    @Column(name = "status", columnDefinition = "tinyint(1) not null default 1 comment '状态'")
    private Status status = Status.ACTIVE;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "createTime", updatable = false, columnDefinition = "datetime not null default NOW() comment '创建时间'")
    private Date createTime;

    @CreatedBy
    @Column(name = "createdBy", columnDefinition = "bigint not null default 1 comment '创建人id'")
    private Long createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "updateTime", columnDefinition = "datetime not null default NOW() comment '最后修改时间'")
    private Date updateTime;

    @LastModifiedBy
    @Column(name = "updatedBy", columnDefinition = "bigint comment '最后修改人id'")
    private Long updatedBy;

    @Version
    @Column(name = "version", nullable = false, columnDefinition = "int not null default 0 comment '版本号'")
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @NonNull
    @Override
    public String toString() {
        Field[] DeclaredFields = this.getClass().getDeclaredFields();
        if (DeclaredFields == null || DeclaredFields.length == 0) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("={");
        for (Field declaredField : DeclaredFields) {
            try {
                // 判断属性是否被static/final/transient修饰
                int modifiers = declaredField.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || Modifier.isTransient(modifiers)) {
                    continue;
                }
                // 判断属性是否含有@ToString.Exclude注解
                if (declaredField.isAnnotationPresent(ToString.Exclude.class)) {
                    continue;
                }
                sb.append(declaredField.getName()).append("=").append(declaredField.get(this)).append(",");
            } catch (IllegalAccessException e) {
                declaredField.setAccessible(true);
                try {
                    sb.append(declaredField.get(this)).append(",");
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } finally {
                    declaredField.setAccessible(false);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    @NonNull
    public BaseEntity exclude() {
        Field[] DeclaredFields = this.getClass().getDeclaredFields();
        if (DeclaredFields == null || DeclaredFields.length == 0) {
            return this;
        }
        for (Field declaredField : DeclaredFields) {

            int modifiers = declaredField.getModifiers();
            // 判断属性是否被final/static修饰
            if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }
            // 判断属性是否被transient修饰 判断属性是否含有@ToString.Exclude注解
            if (Modifier.isTransient(modifiers) || declaredField.isAnnotationPresent(ToString.Exclude.class)) {
                try {
                    declaredField.set(this, null);
                } catch (IllegalAccessException e) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(this, null);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } finally {
                        declaredField.setAccessible(false);
                    }
                }
            }
        }
        return this;
    }
}
