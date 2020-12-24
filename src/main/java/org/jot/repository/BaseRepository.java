package org.jot.repository;

;
import org.jot.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @Author qjj
 * @Date 2020-12-04 10:32
 * @Version 1.0
 * @Class UserRepository.java
 */
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {

}
