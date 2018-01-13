package com.hdu.train.service;

import com.hdu.train.pojo.Train;

import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:32 18-1-12
 * @Modified By:
 */
public interface TrainService {
    List<Train> getAllTrain();
    String getTrainName(int StationId);
    void updateTrainRoute(int trainId,String trainRote);
    int getTrainId(String trainName);
}
