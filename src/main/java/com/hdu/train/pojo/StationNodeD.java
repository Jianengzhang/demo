package com.hdu.train.pojo;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 10:41 18-1-13
 * @Modified By:
 */
public class StationNodeD {
    String stationName1;
    String stationName2;
    int stationId1;
    int stationId2;
    double distance;
    int flag;

    public StationNodeD() {
        this.stationName1 = null;
        this.stationName2 = null;
        this.stationId1 = 0;
        this.stationId2 = 0;
        this.distance = Double.MAX_VALUE;
        this.flag = 0;
    }

    public String getStationName1() {
        return stationName1;
    }

    public void setStationName1(String stationName1) {
        this.stationName1 = stationName1;
    }

    public String getStationName2() {
        return stationName2;
    }

    public void setStationName2(String stationName2) {
        this.stationName2 = stationName2;
    }

    public int getStationId1() {
        return stationId1;
    }

    public void setStationId1(int stationId1) {
        this.stationId1 = stationId1;
    }

    public int getStationId2() {
        return stationId2;
    }

    public void setStationId2(int stationId2) {
        this.stationId2 = stationId2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
