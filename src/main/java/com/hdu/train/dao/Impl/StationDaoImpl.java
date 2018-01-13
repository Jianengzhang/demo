package com.hdu.train.dao.Impl;

import com.hdu.train.dao.SessionFactoryUnit;
import com.hdu.train.dao.StationDao;
import com.hdu.train.pojo.Station;
import org.hibernate.Query;
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
 * @Date: Create in 12:32 18-1-12
 * @Modified By:
 */
@Repository("stationDao")
public class StationDaoImpl implements StationDao {
    @Resource
    private SessionFactory sessionFactory;


    @Override
    public String getStationName(int stationId) {
        List<String> stationList = openSession(sessionFactory).createQuery("select s.stationName from Station s where s.stationId = " + stationId ).list();
        closeSession(SessionFactoryUnit.openSession(sessionFactory));
        if (stationList.size() > 0) {
            return stationList.get(0);
        } else {
            return "0";
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public int getStationId(String stationName) {
        List<Integer> stationList = openSession(sessionFactory).createQuery("select s.stationId from Station s where s.stationName = '" + stationName + "'").list();
        closeSession(openSession(sessionFactory));
        return stationList.get(0).intValue();

    }

    @Override
    public List<String> getAllStation() {
        List stationList = openSession(sessionFactory).createQuery("select s.stationName from Station s").list();
        closeSession(openSession(sessionFactory));
        return (List<String>) stationList;
    }

    @Override
    public void insertStation(String stationName) {
        Transaction tran = openSession(sessionFactory).beginTransaction();//开始事物
        Station station = new Station();
        station.setStationName(stationName);
        openSession(sessionFactory).save(station);//执行
        tran.commit();//提交
        closeSession(openSession(sessionFactory));

    }

    @Override
    public void deleteStation(String stationName) {
        Transaction tran = openSession(sessionFactory).beginTransaction();
        String hql = "Delete FROM Station Where station_name='" + stationName + "'";
        Query q = SessionFactoryUnit.openSession(sessionFactory).createQuery(hql);
        q.executeUpdate();
        tran.commit();
        closeSession(openSession(sessionFactory));
    }

    @Override
    public List<Integer> getAllStationId() {
        List<Integer> stationList = openSession(sessionFactory).createQuery("select u.stationId as stationId from Station u").list();
        closeSession(openSession(sessionFactory));
        return stationList;
    }

    @Override
    public List<Station> getAllStationInfo() {
        List<Station> stationList = openSession(sessionFactory).createQuery("from Station u").list();
        closeSession(openSession(sessionFactory));
        return stationList;
    }
}
