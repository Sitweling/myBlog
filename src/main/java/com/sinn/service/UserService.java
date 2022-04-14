package com.sinn.service;

import com.sinn.pojo.User;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
public interface UserService {

    User checkUser(String username,String password);
}
