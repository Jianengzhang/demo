package com.hdu.train.dao;

import com.hdu.train.pojo.Train;

import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:29 18-1-12
 * @Modified By:
 */
public interface TrainDao {
    //所有车次信息
    List<Train> GetAllTrain();
    //获取车次名
    String getTrainName(int trainId);
    //获取车次ID
    int getTrainId(String trainNme);
    //添加站点到车次
    void updateTrainRoute(int trainId,String trainRoute);
}
