package com.wilson.biz.manager;


import com.wilson.biz.dao.BaseMapper;

import java.util.List;

public abstract class AbstractManager<Entity, EntityExample, Mapper extends BaseMapper<Entity, EntityExample>>
        implements Manager<Entity> {

    protected abstract Mapper getMapper();

    protected abstract EntityExample createExample(Entity condition);

    @Override
    public int save(Entity entity) {
        return getMapper().insert(entity);
    }

    @Override
    public int save(List<Entity> entices) {
        return entices.stream().mapToInt(this::save).sum();
    }

    @Override
    public int delete(Long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int logicalDeleteById(Long id) {
        return getMapper().logicalDeleteByPrimaryKey(id);
    }

    @Override
    public int logicalDeleteByCondition(Entity condition) {
        return getMapper().logicalDeleteByExample(createExample(condition));
    }

    @Override
    public int updateById(long id, Entity entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByCondition(Entity entity, Entity condition) {
        return getMapper().updateByExample(entity, createExample(condition));
    }

    @Override
    public Entity get(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public Entity getWithLogicalDelete(Long id) {
        return getMapper().selectByPrimaryKeyWithLogicalDelete(id, true);
    }


    @Override
    public List<Entity> listByCondition(Entity condition) {
        return getMapper().selectByExample(createExample(condition));
    }

}
