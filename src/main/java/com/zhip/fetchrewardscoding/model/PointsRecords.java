package com.zhip.fetchrewardscoding.model;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Zhipeng Yin
 */


public class PointsRecords {

    private String payer;

    private Integer points;

    private Date timestamp;


    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
