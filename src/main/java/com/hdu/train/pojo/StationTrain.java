package com.hdu.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 11:54 18-1-12
 * @Modified By:
 */
@Entity
@Table(name = "station_train")
public class StationTrain implements Serializable {
    private static final long serialVersionUID = 1L;
    int trainId;
    int stationId;
    double distance;
    Date arriveTime;
    Date leaveTime;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }
}
