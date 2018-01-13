package com.hdu.train.service.impl;

import com.hdu.train.dao.UserDao;
import com.hdu.train.pojo.User;
import com.hdu.train.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:34 18-1-12
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User checkLogin(User user) throws Exception {
        User user1 = userDao.findUserByUserName(user.getUserName());
        //邮箱未注册
        if (null == user1) {
            return null;
        } else {
            if (user1.getPassword().equals(user.getPassword())) {
                return user1;
            }
        }
        return null;
    }
}