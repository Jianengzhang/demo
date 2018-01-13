package com.hdu.train.dao.Impl;

import com.hdu.train.dao.SessionFactoryUnit;
import com.hdu.train.dao.UserDao;
import com.hdu.train.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static com.hdu.train.dao.SessionFactoryUnit.closeSession;
import static com.hdu.train.dao.SessionFactoryUnit.openSession;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:29 18-1-12
 * @Modified By:
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public User findUserByUserName(String userName) {
        List<User> list = openSession(sessionFactory).createQuery("from User u" + " where u.userName = '" + userName + "'").list();
        closeSession(openSession(sessionFactory));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
