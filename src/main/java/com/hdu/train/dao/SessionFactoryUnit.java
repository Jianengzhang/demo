package com.hdu.train.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:33 18-1-7
 * @Modified By:
 */
public class SessionFactoryUnit {
    //打开Session
    public static Session openSession(SessionFactory sessionFactory){
        return  sessionFactory.openSession();
    }

    //关闭Session
    public static  void closeSession(Session session) {
        if (null != session) {
            session.close();
        }
    }
}
