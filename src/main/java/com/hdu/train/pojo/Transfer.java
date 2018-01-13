package com.hdu.train.pojo;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 16:03 18-1-12
 * @Modified By:
 */
public class Transfer {
    int departure;
    int destination;
    Integer[] middle;
    int trainId;
    int trainId2;

    public Transfer(int departure, int destination, Integer[] middle, int trainId, int trainId2) {
        this.departure = departure;
        this.destination = destination;
        this.middle = middle;
        this.trainId = trainId;
        this.trainId2 = trainId2;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Integer[] getMiddle() {
        return middle;
    }

    public void setMiddle(Integer[] middle) {
        this.middle = middle;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getTrainId2() {
        return trainId2;
    }

    public void setTrainId2(int trainId2) {
        this.trainId2 = trainId2;
    }
}
