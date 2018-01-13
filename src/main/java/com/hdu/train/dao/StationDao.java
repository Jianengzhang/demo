package com.hdu.train.dao;

import com.hdu.train.pojo.Station;

import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:31 18-1-12
 * @Modified By:
 */
public interface StationDao {
    //获取站名
    String getStationName(int stationId);
    //获取站ID
    int getStationId(String stationName);
    //获取所有站点信息
    List<String> getAllStation();
    //插入站点信息
    void insertStation(String stationName);
    //删除站点
    void deleteStation(String stationName);
    List<Integer> getAllStationId();
    List<Station> getAllStationInfo();
}
