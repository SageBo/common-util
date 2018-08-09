package com.wilson.biz.manager;

import java.util.List;


public interface Manager<T> {
    int save(T entity);//持久化
    int save(List<T> entitys);//批量持久化
    int delete(Long id);
    int logicalDeleteById(Long id);
    int logicalDeleteByCondition(T condition);
    int updateById(long id, T entity);//更新
    int updateByCondition(T entity, T condition);//更新
    T get(Long id);//通过ID查找
    T getWithLogicalDelete(Long id);//通过ID查找
//    List<T> listByIds(List<Long> ids);
    List<T> listByCondition(T condition);

}
