package com.hdu.train.dao.Impl;

import com.hdu.train.dao.TrainDao;
import com.hdu.train.pojo.Train;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
@Repository("trainDao")
public class TrainDaoImpl implements TrainDao {
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Train> GetAllTrain() {
        List<Train> trainList = openSession(sessionFactory).createQuery("from Train t").list();
        closeSession(openSession(sessionFactory));
        return trainList;
    }

    @SuppressWarnings("unchecked")
    public String getTrainName(int trainId) {
        List<String> trainList = openSession(sessionFactory).createQuery("Select t.trainName from Train t where t.trainId=" + trainId).list();
        closeSession(openSession(sessionFactory));
        return trainList.get(0);
    }

    @SuppressWarnings("unchecked")
    public int getTrainId(String trainNme) {
        List<Integer> trainList = openSession(sessionFactory).createQuery("Select t.trainId from Train t where t.trainName='" + trainNme + "'").list();
        closeSession(openSession(sessionFactory));
        return trainList.get(0).intValue();
    }

    @Override
    public void updateTrainRoute(int trainId, String trainRoute) {
        Transaction tran = openSession(sessionFactory).beginTransaction();//开始事务
        Train t = (Train) openSession(sessionFactory).get(Train.class, trainId);
        t.setTrainRoute(trainRoute);
        openSession(sessionFactory).update(t);//执行
        tran.commit();//提交
        closeSession(openSession(sessionFactory));

    }
}
