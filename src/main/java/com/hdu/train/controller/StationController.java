package com.hdu.train.controller;

import com.hdu.train.service.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:36 18-1-12
 * @Modified By:
 */
@Controller
@RequestMapping("/station")
public class StationController {
    @Resource
    private StationService stationService;

    @RequestMapping("/option")
    public
    @ResponseBody
    Map<String, Object> getAllStation() {
        Map<String, Object> modeMap = new HashMap<>();
        List<String> stations = stationService.getAllStation();
        modeMap.put("data", stations);
        return modeMap;
    }

    @RequestMapping("/showAll")
    public ModelAndView showAll() {
        List<String> stations = stationService.getAllStation();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stations", stations);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/addStation")
    public
    @ResponseBody
    int addStation(HttpServletRequest requests) {
        stationService.insertStation(requests.getParameter("station"));
        return 1;
    }

    @RequestMapping("/deleteStation")
    public
    @ResponseBody
    int deleteStation(HttpServletRequest requests) {
        String stationName = requests.getParameter("station");
        stationService.deleteStation(stationName);
        return 1;
    }
}
