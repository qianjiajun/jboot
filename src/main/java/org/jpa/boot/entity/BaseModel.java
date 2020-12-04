package org.jpa.boot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author qjj
 * @Date 2020-12-04 10:35
 * @Version 1.0
 * @Class BaseModel.java
 * JOINED：继承的子类各自生成一张表
 * SINGLE_TABLE：只生成一张表，用一个字段当鉴别器
 * TABLE_PER_CLASS：每个类都生产一张表，包括基类
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "status", nullable = false, length = 1)
    private int status;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "createdBy")
    private Long createdBy;

    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "updatedBy")
    private Long updatedBy;

    public BaseModel() {
    }

    public BaseModel(Long createdBy) {
        this.createTime = new Date();
        this.createdBy = createdBy;
        this.status = 1;
    }

    public BaseModel(Long id, Long updatedBy) {
        this.id = id;
        this.updateTime = new Date();
        this.updatedBy = updatedBy;
    }

    public BaseModel(Long id, Date updateTime, Long updatedBy) {
        this.id = id;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
