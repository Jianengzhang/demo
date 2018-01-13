package com.hdu.train.controller;

import com.hdu.train.dao.StationDao;
import com.hdu.train.dao.StationTrainDao;
import com.hdu.train.dao.TrainDao;
import com.hdu.train.pojo.StationTrain;
import com.hdu.train.service.StationService;
import com.hdu.train.service.StationTrainService;
import com.hdu.train.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:36 18-1-12
 * @Modified By:
 */
@Controller
@RequestMapping(value = "/train")
public class TrainController {
    @Resource
    private StationTrainService stationTrainService;
    @Resource
    private TrainService trainService;
    @Resource
    private StationService stationService;

    @RequestMapping("/add")
    public
    @ResponseBody
    int addStationToTrain(HttpServletRequest requests) throws ParseException {
        String train = requests.getParameter("train");
        int trainId = trainService.getTrainId(train);
        String station = requests.getParameter("station");
        int stationId = stationService.getStationId(station);
        String arrive = requests.getParameter("arrive");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date arriveTime = dateFormat.parse(arrive);
        String leave = requests.getParameter("leave");
        Date leaveTime = dateFormat.parse(leave);
        String distance = requests.getParameter("distance");
        double dis = Double.valueOf(distance);
        stationTrainService.addStationToTrian(trainId, stationId, arriveTime, leaveTime, dis);
        return 1;
    }

}
