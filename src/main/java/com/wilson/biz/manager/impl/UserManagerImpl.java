package com.wilson.biz.manager.impl;


import com.wilson.biz.dao.UserMapper;
import com.wilson.biz.entity.UserDO;
import com.wilson.biz.entity.UserDOExample;
import com.wilson.biz.manager.AbstractManager;
import com.wilson.biz.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserManagerImpl extends AbstractManager<UserDO, UserDOExample, UserMapper> implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }


    @Override
    protected UserDOExample createExample(UserDO condition) {

        UserDOExample example = new UserDOExample();
        // TODO: 2018/7/28
        return example;
    }
}
