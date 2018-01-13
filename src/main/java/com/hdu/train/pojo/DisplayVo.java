package com.hdu.train.pojo;

import java.util.Date;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:12 18-1-12
 * @Modified By:
 */
public class DisplayVo {
    String StationName1;
    String StationName2;
    String TrainName;
    Date leaveTime;
    Date arriveTime;
    double distance;

    public DisplayVo(String stationName1, String stationName2, String trainName, Date leaveTime, Date arriveTime, double distance) {
        StationName1 = stationName1;
        StationName2 = stationName2;
        TrainName = trainName;
        this.leaveTime = leaveTime;
        this.arriveTime = arriveTime;
        this.distance = distance;
    }

    public String getStationName1() {
        return StationName1;
    }

    public void setStationName1(String stationName1) {
        StationName1 = stationName1;
    }

    public String getStationName2() {
        return StationName2;
    }

    public void setStationName2(String stationName2) {
        StationName2 = stationName2;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
