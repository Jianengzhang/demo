package com.hdu.train.dao;

import com.hdu.train.pojo.User;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:29 18-1-12
 * @Modified By:
 */
public interface UserDao {
    User findUserByUserName(String userName);
}
