package com.hdu.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 15:25 18-1-7
 * @Modified By:
 */

@Entity
@Table(name = "station")
public class Station {
    int stationId;
    String stationName;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
