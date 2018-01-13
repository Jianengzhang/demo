package com.hdu.train.service.impl;

import com.hdu.train.dao.TrainDao;
import com.hdu.train.pojo.Train;
import com.hdu.train.service.TrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:35 18-1-12
 * @Modified By:
 */
@Service
public class TrainServiceImpl implements TrainService {
    @Resource
    private TrainDao trainDao;

    @Override
    public List<Train> getAllTrain() {
        return trainDao.GetAllTrain();
    }

    @Override
    public String getTrainName(int stationId) {
        return trainDao.getTrainName(stationId);
    }

    @Override
    public void updateTrainRoute(int trainId, String trainRote) {
        trainDao.updateTrainRoute(trainId, trainRote);
    }

    @Override
    public int getTrainId(String trainName) {
        return trainDao.getTrainId(trainName);
    }
}
