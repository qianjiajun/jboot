package org.jot.service.base.impl;

import org.jot.entity.BaseEntity;
import org.jot.repository.BaseRepository;
import org.jot.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @Author qjj
 * @Date 2020-12-22 11:45
 * @Version 1.0
 * @Class BaseService.java
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    private BaseRepository<T> baseRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T add(T t) {
        return baseRepository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T edit(T t) {
        return baseRepository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(Serializable id) {
        baseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public T findById(Serializable id) {
        Optional<T> optional = baseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public T getOne(Serializable id) {
        return baseRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public T findOne(Specification<T> t) {
        Optional<T> optional = baseRepository.findOne(t);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findList(Specification<T> e) {
        return baseRepository.findAll(e);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<T> findPage(Specification<T> e, Pageable pageable) {
        return baseRepository.findAll(e, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Long countOne(Specification<T> e) {
        return baseRepository.count(e);
    }
}
