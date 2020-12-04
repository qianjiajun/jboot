package org.jpa.boot.entity;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class Role.java
 */
@ToString
@Entity
@Table(name = "role")
public class Role extends BaseModel {

    @Column(name = "code", length = 36)
    private String code;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    public Role(){}

    public Role(String code, String name, Long createdBy) {
        super(createdBy);
        this.code = code;
        this.name = name;
    }

    public Role(Long id, String code, String name, Long updatedBy) {
        super(id, updatedBy);
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
