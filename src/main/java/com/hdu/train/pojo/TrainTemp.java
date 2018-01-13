package com.hdu.train.pojo;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:11 18-1-12
 * @Modified By:
 */
public class TrainTemp {
    int trainId;
    Integer[] trainRoute;

    public TrainTemp(int trainId, Integer[] trainRoute) {
        this.trainId = trainId;
        this.trainRoute = trainRoute;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Integer[] getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(Integer[] trainRoute) {
        this.trainRoute = trainRoute;
    }
}
