package com.hdu.train.service;

import com.hdu.train.pojo.StationNode;
import com.hdu.train.pojo.TrainTemp;

import java.util.Date;
import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:33 18-1-12
 * @Modified By:
 */
public interface StationTrainService {
    List<StationNode> getDirectStation(int stationId1, int stationId2);

    StationNode getTransferTrain(int stationId1, int stationId2, int trainId);

    void addStationToTrian(int trian, int station, Date arriveTime, Date leaveTime, double distance);

    List<TrainTemp> getTrainRoute();

    StationNode getTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance);

    double getDistance(int stationId1, int stationId2);

    StationNode getStationTrainByDistance(Integer integer, Integer integer1, double distance);
}
