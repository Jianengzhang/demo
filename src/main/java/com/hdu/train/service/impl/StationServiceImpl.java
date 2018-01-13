package com.hdu.train.service.impl;

import com.hdu.train.dao.StationDao;
import com.hdu.train.pojo.Station;
import com.hdu.train.service.StationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:34 18-1-12
 * @Modified By:
 */
@Service
public class StationServiceImpl implements StationService {
    @Resource
    private StationDao stationDao;

    @Override
    public List<String> getAllStation() {
        return stationDao.getAllStation();
    }

    @Override
    public int getStationId(String stationName) {
        return stationDao.getStationId(stationName);
    }

    @Override
    public String getStationName(int stationId) {
        return stationDao.getStationName(stationId);
    }

    @Override
    public void insertStation(String stationName) {
        stationDao.insertStation(stationName);
    }

    @Override
    public void deleteStation(String stationName) {
        stationDao.deleteStation(stationName);
    }

    @Override
    public List<Integer> getAllStationId() {
        return stationDao.getAllStationId();
    }

    @Override
    public List<Station> getAllStationInfo() {
        return stationDao.getAllStationInfo();
    }
}
