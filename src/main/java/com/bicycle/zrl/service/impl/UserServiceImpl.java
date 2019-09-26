package com.bicycle.zrl.service.impl;

import com.bicycle.zrl.entity.User;
import com.bicycle.zrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean sendCode(Map<String, Object> map) {
        //生成6位随机数
        String code = (int)((Math.random()*9+1)*100000) + "";
        System.out.println("你的登录验证码为："+code+",手机号为："+map.get("phoneNum").toString());
        //将发送的手机号作为key，验证码作为value保存到redis中
        stringRedisTemplate.opsForValue().set(map.get("phoneNum").toString(),code,300, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 校验验证码
     * @param map
     * @return
     */
    @Override
    public String checkCode(Map<String, Object> map) {
        //通过手机号获取redis中存储的验证码
        String code = stringRedisTemplate.opsForValue().get(map.get("phoneNum").toString());
        if(code == null){
            //没有发送验证码
            return "nocode";
        }
        //将用户传入code与实际对比
        if(code != null && code.equals(map.get("verifyCode").toString())){
            return "success";
        }
        return "error";
    }

    /**
     * 将用户信息保存到mongodb中
     * @param user
     * @return
     */
    @Override
    public void registerUser(User user) {
        mongoTemplate.insert(user);
    }

    /**
     * 更新用户信息
     * @param map
     * @return
     */
    @Override
    public void updateUser(Map<String, Object> map) {
        //根据用户的手机号，对信息进行跟新
        //注意，手机号不是主键
        Update update = new Update();
        if(map.get("deposit") != null){
            update.set("deposit",map.get("deposit").toString());
        }
        if(map.get("status") != null){
            update.set("status",map.get("status").toString());
        }
        if(map.get("name") != null){
            update.set("name",map.get("name").toString());
        }
        if(map.get("idNum") != null){
            update.set("idNum",map.get("idNum").toString());
        }
        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(map.get("phoneNum").toString())),
                update,User.class);
    }

}
