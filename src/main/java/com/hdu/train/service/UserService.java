package com.hdu.train.service;

import com.hdu.train.pojo.User;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:33 18-1-12
 * @Modified By:
 */
public interface UserService {
    User checkLogin(User user) throws Exception;
}
