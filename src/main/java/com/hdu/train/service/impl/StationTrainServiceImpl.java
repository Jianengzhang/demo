package com.hdu.train.service.impl;

import com.hdu.train.dao.StationTrainDao;
import com.hdu.train.pojo.StationNode;
import com.hdu.train.pojo.TrainTemp;
import com.hdu.train.service.StationTrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:33 18-1-12
 * @Modified By:
 */
@Service
public class StationTrainServiceImpl implements StationTrainService {
    @Resource
    private StationTrainDao stationTrainDao;

    @Override
    public List<StationNode> getDirectStation(int stationId1, int stationId2) {
        return stationTrainDao.GetDirectStation(stationId1, stationId2);
    }

    @Override
    public StationNode getTransferTrain(int stationId1, int stationId2, int trainId) {
        return stationTrainDao.getTransferTrain(stationId1, stationId2, trainId);
    }

    @Override
    public void addStationToTrian(int trian, int station, Date arriveTime, Date leaveTime, double distance) {
        stationTrainDao.addStationToTrain(trian, station, arriveTime, leaveTime, distance);
    }

    @Override
    public List<TrainTemp> getTrainRoute() {
        List<TrainTemp> trainTemps = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < stationTrainDao.getTrainSum().size(); i++) {
            Integer[] integers = new Integer[stationTrainDao.getTrainSum().get(i).intValue()];
            for (int j = 0; j < stationTrainDao.getTrainSum().get(i); j++) {
                integers[j] = stationTrainDao.getRecordSum().get(count).getStationId();
                count++;
            }
            trainTemps.add(new TrainTemp(stationTrainDao.getAllTrainId().get(i), integers));
        }
        return trainTemps;
    }

    @Override
    public StationNode getTransferTrainByDistance(int stationId1, int stationId2, int trainId, double distance) {
        StationNode trainQuery = stationTrainDao.getTransferTrainByDistance(stationId1, stationId2,trainId, distance);
        if (trainQuery == null) {
            return null;
        } else {
            return trainQuery;
        }
    }

    @Override
    public double getDistance(int stationId1, int stationId2) {
        List<Double> distanceList = stationTrainDao.getDistance(stationId1, stationId2);
        return Collections.min(distanceList);
    }

    @Override
    public StationNode getStationTrainByDistance(Integer integer, Integer integer1, double distance) {
        return stationTrainDao.getStationTrainByDistance(integer.intValue(), integer1.intValue(), distance);
    }
}
