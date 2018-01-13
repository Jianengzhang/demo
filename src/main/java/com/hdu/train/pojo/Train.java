package com.hdu.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:30 18-1-7
 * @Modified By:
 */
@Entity
@Table(name = "train")
public class Train {
    private int trainId;
    private String trainName;
    private String trainRoute;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainRoute() {
        return trainRoute;
    }

    public void setTrainRoute(String trainRoute) {
        this.trainRoute = trainRoute;
    }
}
