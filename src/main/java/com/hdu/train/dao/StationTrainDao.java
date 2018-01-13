package com.hdu.train.dao;

import com.hdu.train.pojo.StationNode;
import com.hdu.train.pojo.StationTrain;

import java.util.Date;
import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:31 18-1-12
 * @Modified By:
 */

public interface StationTrainDao {
    List<StationNode> GetDirectStation(int stationId1, int stationId2);
    StationNode getTransferTrain(int stationId1,int stationId2,int trainId);
    //添加站点到车次信息
    void addStationToTrain(int trainId, int stationId, Date arriveTime, Date leaveTime, double distance);

    //获取车次数目
    List<Integer> getTrainSum();

    //获取记录数目
    List<StationTrain> getRecordSum();

    List<Integer> getAllTrainId();

    StationNode getTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance);

    List<Double> getDistance(int stationId1, int stationId2);

    StationNode getStationTrainByDistance(int i, int i1, double distance);
}
