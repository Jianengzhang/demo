package com.hdu.train.dao.Impl;

import com.hdu.train.dao.SessionFactoryUnit;
import com.hdu.train.dao.StationTrainDao;
import com.hdu.train.pojo.StationNode;
import com.hdu.train.pojo.StationTrain;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hdu.train.dao.SessionFactoryUnit.closeSession;
import static com.hdu.train.dao.SessionFactoryUnit.openSession;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:31 18-1-12
 * @Modified By:
 */
@Repository("stationNodeDao")
public class StationTrainDaoImpl implements StationTrainDao {
    @Resource
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<StationNode> GetDirectStation(int stationId1, int stationId2) {
        List<StationNode> stationList = openSession(sessionFactory).createQuery
                ("select ST1.stationId as stationId1, ST2.stationId as stationId2,ST1.trainId as trainId,ST1.leaveTime as leaveTime,ST2.arriveTime as arriveTime,ST2.distance-ST1.distance as distance" +
                        " from StationTrain ST1,StationTrain ST2" +
                        " where ST1.stationId = " + stationId1 + " and ST2.stationId=" + stationId2 + " and ST1.trainId = ST2.trainId and ST1.distance < ST2.distance").setResultTransformer(Transformers.aliasToBean(StationNode.class)).list();
        System.out.println(stationList);
        closeSession(openSession(sessionFactory));
        return stationList;
    }

    @SuppressWarnings("unchecked")
    public StationNode getTransferTrain(int stationId1, int stationId2, int trainId) {
        List<StationNode> stationList = openSession(sessionFactory).createQuery
                ("select ST1.stationId as stationId1, ST2.stationId as stationId2,ST1.trainId as trainId,ST1.leaveTime as leaveTime,ST2.arriveTime as arriveTime,ST2.distance-ST1.distance as distance" +
                        " from StationTrain ST1,StationTrain ST2" +
                        " where ST1.stationId = " + stationId1 + " and ST2.stationId=" + stationId2 + " and ST1.trainId = ST2.trainId and ST1.distance < ST2.distance " + " and ST1.trainId=" + trainId).setResultTransformer(Transformers.aliasToBean(StationNode.class)).list();
        System.out.println(stationList);
        closeSession(SessionFactoryUnit.openSession(sessionFactory));
        return stationList.get(0);
    }


    @Override
    public void addStationToTrain(int trainId, int stationId, Date arriveTime, Date leaveTime, double distance) {
        Transaction tran = openSession(sessionFactory).beginTransaction();//开始事物
        StationTrain stationTrain = new StationTrain();
        stationTrain.setTrainId(trainId);
        stationTrain.setStationId(stationId);
        stationTrain.setArriveTime(arriveTime);
        stationTrain.setLeaveTime(leaveTime);
        stationTrain.setDistance(distance);
        openSession(sessionFactory).save(stationTrain);//执行
        tran.commit();//提交
        closeSession(openSession(sessionFactory));
    }

    @Override
    public List<Integer> getTrainSum() {
        List<Long> list = openSession(sessionFactory).createQuery("select count(ST.trainId) from StationTrain ST group by ST.trainId").list();
        closeSession(openSession(sessionFactory));
        List<Integer> list1= new ArrayList<>();
        for (long l :list) {
            list1.add(new Integer((int)l));
        }
        return list1;
    }

    @Override
    public List<StationTrain> getRecordSum() {
        List<StationTrain> list = openSession(sessionFactory).createQuery("from StationTrain ST Order by ST.trainId,ST.distance").list();
        closeSession(openSession(sessionFactory));
        return list;
    }

    @Override
    public List<Integer> getAllTrainId() {
        List<Integer> list = openSession(sessionFactory).createQuery("select distinct ST.trainId from StationTrain ST order by ST.trainId").list();
        closeSession(openSession(sessionFactory));
        return list;
    }

    @Override
    public StationNode getTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance) {
        List<StationNode> trainStationList = openSession(sessionFactory).createQuery("select u.trainId as trainId, u.stationId as stationId1, a.stationId as stationId2, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from StationTrain u, StationTrain a where u.stationId = " + stationId1 + " and a.stationId = " + stationId2 + " and u.trainId = a.trainId and u.trainId = " + trainId + " and u.distance < a.distance and a.distance - u.distance - " + distance + "<=0.0000001").setResultTransformer(Transformers.aliasToBean(StationNode.class)).list();
        closeSession(openSession(sessionFactory));
        if (trainStationList.size() <= 0){
            return null;
        }
        return trainStationList.get(0);
    }

    @Override
    public List<Double> getDistance(int stationId1, int stationId2) {
        List<Double> stationList = openSession(sessionFactory).createQuery("select a.distance - u.distance as distance from StationTrain u, StationTrain a where u.stationId = " + stationId1 + " and a.stationId = " + stationId2 + " and u.trainId = a.trainId and u.distance < a.distance").list();
        System.out.println(stationList);
        closeSession(openSession(sessionFactory));
        return stationList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StationNode getStationTrainByDistance(int i, int i1, double distance) {
        List<StationNode> trainStationList = openSession(sessionFactory).createQuery("select u.trainId as trainId, u.stationId as stationId1, a.stationId as stationId2, a.distance - u.distance as distance, u.leaveTime as arriveTime, a.arriveTime as leaveTime from StationTrain u, StationTrain a where u.stationId = " + i + " and a.stationId = " + i1 + " and u.trainId = a.trainId and u.distance < a.distance and a.distance - u.distance - " + distance + "<=0.0000001").setResultTransformer(Transformers.aliasToBean(StationNode.class)).list();
        closeSession(openSession(sessionFactory));
        return trainStationList.get(0);
    }
}
