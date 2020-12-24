package org.jot.service.base;

import org.jot.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @Author qjj
 * @Date 2020-12-22 11:44
 * @Version 1.0
 * @Interface IBaseService.java
 */
public interface IBaseService<T extends BaseEntity> {

    /**
     * 新增
     *
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 修改
     *
     * @param t
     * @return
     */
    T edit(T t);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    void remove(Serializable id);

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    T findById(Serializable id);

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    T getOne(Serializable id);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();


    /**
     * 查询一条数据
     *
     * @param e
     * @return
     */
    T findOne(Specification<T> e);

    /**
     * 查询列表
     *
     * @param e
     * @return
     */
    List<T> findList(Specification<T> e);

    /**
     * 查询分页
     *
     * @param e
     * @param pageable
     * @return
     */
    Page<T> findPage(Specification<T> e, Pageable pageable);

    /**
     * 查询条数
     *
     * @param e
     * @return
     */
    Long countOne(Specification<T> e);


}
