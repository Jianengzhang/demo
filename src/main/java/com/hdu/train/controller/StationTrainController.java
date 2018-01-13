package com.hdu.train.controller;

import com.hdu.train.pojo.*;
import com.hdu.train.service.StationService;
import com.hdu.train.service.StationTrainService;
import com.hdu.train.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:36 18-1-12
 * @Modified By:
 */
@Controller
@RequestMapping("/search")
public class StationTrainController {
    @Resource
    private StationService stationService;
    @Resource
    private TrainService trainService;
    @Resource
    private StationTrainService stationTrainService;

    List<TrainTemp> trainTemps;
    List<TrainTemp> departureTemps;
    List<TrainTemp> destinationTemps;

    String departure;
    String destination;
    int stationId1;
    int stationId2;
    Map<Integer, String> trainMap = new HashMap<>();
    Map<Integer, String> stationMap = new HashMap<>();

    @RequestMapping("/direct")
    public ModelAndView direct(HttpServletRequest requests) {
        ModelAndView modelAndView = new ModelAndView();
        this.departure = requests.getParameter("departure");
        this.destination = requests.getParameter("destination");
        this.stationId1 = stationService.getStationId(departure);
        this.stationId2 = stationService.getStationId(destination);
        List<Station> stations = new ArrayList<>();
        stations = stationService.getAllStationInfo();
        for (Station s : stations) {
            stationMap.put(s.getStationId(), s.getStationName());
        }
        List<Train> trains = new ArrayList<>();
        trains = trainService.getAllTrain();
        for (Train t : trains) {
            trainMap.put(t.getTrainId(), t.getTrainName());
        }

        List<DisplayVo> displayVos = new ArrayList<>();
        //获取包含stationId1,stationId2的信息
        List<StationNode> stationNodes = stationTrainService.getDirectStation(stationId1, stationId2);
        if (0 >= stationNodes.size()) {
            modelAndView.addObject("NODirect", "请使用高级查询");
        }

        Calendar calendar = new GregorianCalendar();
        for (StationNode s : stationNodes) {
            //数据库中只有时间没有日期，所以利用Calendar获取日期
            s.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), s.getArriveTime().getHours(), s.getArriveTime().getMinutes(), s.getArriveTime().getSeconds()));
            s.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), s.getLeaveTime().getHours(), s.getLeaveTime().getMinutes(), s.getLeaveTime().getSeconds()));
            displayVos.add(new DisplayVo(stationService.getStationName(s.getStationId1()),
                    stationService.getStationName(s.getStationId2()),
                    trainService.getTrainName(s.getTrainId()),
                    s.getLeaveTime(),
                    s.getArriveTime(),
                    s.getDistance()
            ));
        }
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/advanced")
    public ModelAndView advanced() {
        List<StationNode> stationNodes = new ArrayList<>();
        List<DisplayVo> displayVos = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        //获取所有列车信息
        //getAllTrain();
        trainTemps = stationTrainService.getTrainRoute();
        //获取包含出发站的车次信息
        getAllDeparture(stationId1);
        //获取包含目的站的车次信息
        getAllDestination(stationId2);
        if (getMiddleStation(stationId1, stationId2).size() > 0) {
            for (AdvancedSearchVo ad : getMiddleStation(stationId1, stationId2)
                    ) {
                if (!(stationTrainService.getTransferTrain(ad.getStationId1(), ad.getStationId2(), ad.getTrainId()).equals("0"))) {
                    stationNodes.add(stationTrainService.getTransferTrain(ad.getStationId1(), ad.getStationId2(), ad.getTrainId()));
                    System.out.println(ad.toString());
                }

            }
        }
        Calendar calendar = Calendar.getInstance();
        if (stationNodes.size() > 0) {
            for (StationNode s : stationNodes
                    ) {
                Date date = new Date();
                s.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), s.getArriveTime().getHours(), s.getArriveTime().getMinutes(), s.getArriveTime().getSeconds()));
                s.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), s.getLeaveTime().getHours(), s.getLeaveTime().getMinutes(), s.getLeaveTime().getSeconds()));
                displayVos.add(new DisplayVo(
                        stationMap.get(s.getStationId1()),
                        stationMap.get(s.getStationId2()),
                        trainMap.get(s.getTrainId()),
                        s.getLeaveTime(),
                        s.getArriveTime(),
                        s.getDistance()
                ));
            }
        }
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/StationDijkstra")
    public ModelAndView StationDijkstra() {
        ModelAndView modelAndView = new ModelAndView();
        trainTemps = stationTrainService.getTrainRoute();
        StationNodeD[][] arr = StationDigraph();
        List<Integer> single = StationDijkstra(arr, stationId1, stationId2);
        System.out.println(single);
        int station1 = single.get(0);
        int station2 = single.get(1);
        int train = 0;
        List<DisplayVo> displayVos = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        StationNode trainQueryList;
        for (int i = 0; i < single.size() - 1; i++) {
            if (train == 0) {
                trainQueryList = stationTrainService.getStationTrainByDistance(single.get(i), single.get(i + 1), arr[single.get(i)][single.get(i + 1)].getDistance());
                train = trainQueryList.getTrainId();
                System.out.println("train = " + train);
            } else if ((trainQueryList = stationTrainService.getTransferTrainByDistance(single.get(i), single.get(i + 1), train, arr[single.get(i)][single.get(i + 1)].getDistance())) != null) {
                station2 = trainQueryList.getStationId2();
            } else {
                trainQueryList = stationTrainService.getStationTrainByDistance(single.get(i), single.get(i + 1), arr[single.get(i)][single.get(i + 1)].getDistance());
                StationNode trainQuery = stationTrainService.getTransferTrainByDistance(station1, station2, train, arr[station1][station2].getDistance());
                Date date = new Date();
                trainQuery.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getArriveTime().getHours(), trainQuery.getArriveTime().getMinutes(), trainQuery.getArriveTime().getSeconds()));
                trainQuery.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getLeaveTime().getHours(), trainQuery.getLeaveTime().getMinutes(), trainQuery.getLeaveTime().getSeconds()));
                displayVos.add(new DisplayVo(
                        stationMap.get(trainQuery.getStationId1()),
                        stationMap.get(trainQuery.getStationId2()),
                        trainMap.get(trainQuery.getTrainId()),
                        trainQuery.getArriveTime(),
                        trainQuery.getLeaveTime(),
                        trainQuery.getDistance()
                ));
                station1 = trainQueryList.getStationId1();
                station2 = trainQueryList.getStationId2();
                train = trainQueryList.getTrainId();
            }
        }
        StationNode trainQuery = stationTrainService.getTransferTrainByDistance(station1, station2, train, arr[station1][station2].getDistance());
        trainQuery.setArriveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getArriveTime().getHours(), trainQuery.getArriveTime().getMinutes(), trainQuery.getArriveTime().getSeconds()));
        trainQuery.setLeaveTime(new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), trainQuery.getLeaveTime().getHours(), trainQuery.getLeaveTime().getMinutes(), trainQuery.getLeaveTime().getSeconds()));
        displayVos.add(new DisplayVo(
                stationMap.get(trainQuery.getStationId1()),
                stationMap.get(trainQuery.getStationId2()),
                trainMap.get(trainQuery.getTrainId()),
                trainQuery.getArriveTime(),
                trainQuery.getLeaveTime(),
                trainQuery.getDistance()
        ));
        modelAndView.addObject("TrainRoute", displayVos);
        modelAndView.setViewName("success");
        return modelAndView;

    }

    //构建站点图，二维数组
    private StationNodeD[][] StationDigraph() {
        int length = stationService.getAllStation().size();
        StationNodeD[][] arr = new StationNodeD[length + 1][length + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                arr[i][j] = new StationNodeD();
            }
        }
        for (TrainTemp t : trainTemps) {
            int ret[] = new int[t.getTrainRoute().length];
            ret = intUnPack(t.getTrainRoute());
            for (int i = 0; i < ret.length - 1; i++) {
                System.out.println(ret[i]);
                System.out.println(ret[i + 1]);
                System.out.println();
                if (arr[ret[i]][ret[i + 1]].getFlag() == 0) {
                    arr[ret[i]][ret[i + 1]].setStationId1(ret[i]);
                    arr[ret[i]][ret[i + 1]].setStationId2(ret[i + 1]);
                    arr[ret[i]][ret[i + 1]].setStationName1(stationService.getStationName(ret[i]));
                    arr[ret[i]][ret[i + 1]].setStationName2(stationService.getStationName(ret[i + 1]));
                    arr[ret[i]][ret[i + 1]].setDistance(stationTrainService.getDistance(ret[i], ret[i + 1]));
                    arr[ret[i]][ret[i + 1]].setFlag(1);
                }
            }

        }
        return arr;
    }

    //Dijkstra算法计算最短路径，返回经过的站点Id
    private List<Integer> StationDijkstra(StationNodeD[][] arr, int stationId1, int stationId2) {
        int length = stationService.getAllStation().size();
        List<Integer> single = new ArrayList<>();
        List<Integer> station = stationService.getAllStationId();
        int[][] array = new int[length + 1][length + 1];
        System.out.println(length);
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                array[i][j] = 0;
            }
        }
        double[] distance = new double[length + 1];
        int[] left = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            distance[i] = Integer.MAX_VALUE;
            left[i] = 0;
        }
        distance[stationId1] = 0;
        left[stationId1] = stationId1;
        int flag = stationId1;
        int count = 0;
        double dis = Double.MAX_VALUE;
        for (int n = 0; n < length; n++) {
            dis = Integer.MAX_VALUE;
            for (int i = 1; i <= length; i++) {
                if (arr[flag][i].getFlag() != 0) {
                    if (arr[flag][i].getDistance() + distance[flag] <= distance[i]) {
                        distance[i] = arr[flag][i].getDistance() + distance[flag];
                        left[i] = flag;
                        System.out.println("+++++++++++");
                        System.out.println(left[i]);
                        if (distance[i] < dis) {
                            dis = distance[i];
                            count = i;
                        }
                    }
                }
            }
            flag = count;
        }
        single.add(stationId2);
        for (int i = stationId2; left[i] != stationId1; i = left[i]) {
            single.add(left[i]);
            System.out.println("--------");
            System.out.println(left[i]);
        }
        single.add(stationId1);
        Collections.reverse(single);
        return single;
    }



/*
    //对所有的列车信息进行处理，并存入List<TrainTemp>中
    private List<TrainTemp> getAllTrain() {
        List<Train> trains = trainService.getAllTrain();
        trainTemps = new ArrayList<>();
        for (Train t : trains) {
            Integer ret[] = new Integer[t.getTrainRoute().split(",").length];
            for (int i = 0, j = 0; i < t.getTrainRoute().split(",").length; i++, j++) {
                ret[j] = Integer.parseInt(t.getTrainRoute().split(",")[i]);
            }
            trainTemps.add(new TrainTemp(t.getTrainId(), ret));
        }
        return trainTemps;
    }
*/

    private void getAllDeparture(int stationId) {
        departureTemps = new ArrayList<>();
        int flag = 0;
        int index = 0;
        for (TrainTemp t : trainTemps
                ) {
            for (int i = 0; i < t.getTrainRoute().length; i++) {
                if (t.getTrainRoute()[i].equals(stationId)) {
                    flag = 1;
                    index = i;
                    break;
                }
            }
            if (flag == 1) {
                Integer[] ret = new Integer[t.getTrainRoute().length - index];
                for (int j = index, k = 0; j < t.getTrainRoute().length; j++, k++) {
                    ret[k] = t.getTrainRoute()[j];
                }
                departureTemps.add(new TrainTemp(t.getTrainId(), ret));
            }
            flag = 0;
            index = 0;
        }
        for (TrainTemp t : departureTemps
                ) {
            System.out.println(t.getTrainId());
            System.out.println(Arrays.toString(t.getTrainRoute()));
        }
    }

    private void getAllDestination(int stationId) {
        destinationTemps = new ArrayList<>();
        int flag = 0;
        int index = 0;
        for (TrainTemp t : trainTemps
                ) {
            for (int i = 0; i < t.getTrainRoute().length; i++) {
                if (t.getTrainRoute()[i].equals(stationId)) {
                    flag = 1;
                    index = i;
                    break;
                }
            }
            if (flag == 1) {
                Integer[] ret = new Integer[index + 1];
                for (int j = 0, k = 0; j <= index; j++, k++) {
                    ret[k] = t.getTrainRoute()[j];
                }
                destinationTemps.add(new TrainTemp(t.getTrainId(), ret));
            }
            flag = 0;
            index = 0;
        }
        for (TrainTemp t : destinationTemps
                ) {
            System.out.println(t.getTrainId());
            System.out.println(Arrays.toString(t.getTrainRoute()));
        }
    }

    private List<AdvancedSearchVo> getMiddleStation(int stationId1, int stationId2) {
        List<AdvancedSearchVo> advancedSearchVos = new ArrayList<>();
        List<Transfer> transferList = new ArrayList<>();
        for (TrainTemp t1 : departureTemps
                ) {
            for (TrainTemp t2 : destinationTemps
                    ) {
                if (intersect(t1.getTrainRoute(), t2.getTrainRoute()).length > 0) {
                    transferList.add(new Transfer(stationId1, stationId2, intersect(t1.getTrainRoute(), t2.getTrainRoute()), t1.getTrainId(), t2.getTrainId()));
                }
            }

        }
        for (Transfer t : transferList
                ) {
            for (Integer integer : t.getMiddle()
                    ) {
                advancedSearchVos.add(new AdvancedSearchVo(stationId1, integer, t.getTrainId()));
                advancedSearchVos.add(new AdvancedSearchVo(integer, stationId2, t.getTrainId2()));
            }

        }
        return advancedSearchVos;
    }

    private static Integer[] intersect(Integer[] arr1, Integer[] arr2) {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(arr1));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(arr2));
        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        Integer[] arr = new Integer[result.size()];
        return result.toArray(arr);
    }

    private int[] intUnPack(Integer[] integers) {
        int[] ints = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i].intValue();
        }
        return ints;
    }
}