package com.hdu.train.service;

import com.hdu.train.pojo.Station;

import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:32 18-1-12
 * @Modified By:
 */
public interface StationService {
    List<String> getAllStation();

    int getStationId(String stationName);

    String getStationName(int stationId);

    void insertStation(String stationName);

    void deleteStation(String stationName);
    List<Integer> getAllStationId();
    List<Station> getAllStationInfo();
}
