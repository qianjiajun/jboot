package org.jot.entity.role;

import org.jot.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class Role.java
 */

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    @Column(name = "code", length = 36)
    private String code;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    public Role() {
    }

    public Role(String code, String name, Long createdBy) {
        this.setCreatedBy(createdBy);
        this.code = code;
        this.name = name;
    }

    public Role(Long id, String code, String name, Long updatedBy) {
        this.setId(id);
        this.setUpdatedBy(updatedBy);
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


    @Override
    public String toString() {
        return super.toString();
    }
}
