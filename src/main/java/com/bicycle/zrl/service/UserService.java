package com.bicycle.zrl.service;

import com.bicycle.zrl.entity.User;

import java.util.Map;

public interface UserService {
    //发送验证码
    boolean sendCode(Map<String, Object> map);

    //校验验证码
    String checkCode(Map<String, Object> map);

    //注册信息
    void registerUser(User user);

    //充值押金
    void updateUser(Map<String, Object> map);
}
