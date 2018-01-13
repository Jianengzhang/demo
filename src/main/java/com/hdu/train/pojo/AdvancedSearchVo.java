package com.hdu.train.pojo;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 16:01 18-1-12
 * @Modified By:
 */
public class AdvancedSearchVo {
    int stationId1;
    int stationId2;
    int trainId;

    public AdvancedSearchVo(int stationId1, int stationId2, int trainId) {
        this.stationId1 = stationId1;
        this.stationId2 = stationId2;
        this.trainId = trainId;
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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
