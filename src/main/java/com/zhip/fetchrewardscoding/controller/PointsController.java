package com.zhip.fetchrewardscoding.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhip.fetchrewardscoding.model.PointsRecords;
import com.zhip.fetchrewardscoding.model.Spend;
import com.zhip.fetchrewardscoding.service.PointsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zhipeng Yin
 */
@RestController  // restful function return value as the request return value.
@RequestMapping("/api")
public class PointsController extends BaseController {

    @Resource
    private PointsService pointsService;


    @RequestMapping("/getBalance")
    public Object allBalance(){
        JSONObject record = pointsService.getAll();
        return success(record);
    }

    @RequestMapping("/addTransaction")
    public Object addTransaction(@RequestBody PointsRecords pointsRecords){
        if(pointsRecords.getPoints() == 0){
            return error(202,"Transaction should not contain 0 points");
        }
        if(pointsRecords.getPoints() < 0){
            if(!pointsService.checkExistPayer(pointsRecords)){
                return error(202,"new payer cannot have negative balance");
            }
            JSONObject balance = pointsService.getAll();
            if(!balance.containsKey(pointsRecords.getPayer())){
                return error(202,"no existing payer points");
            }
            Integer points = balance.getInteger(pointsRecords.getPayer());
            if(points - pointsRecords.getPoints() < 0 ){
                return error(202,"Payer cannot have negative balance");
            }
        }
        pointsService.add(pointsRecords);

        return success("Add successful");
    }

    @RequestMapping("/spendPoints")
    public Object spendPoints(@RequestBody Spend spend){
        if(spend.getPoints() == null){
            return success("Please enter the correct points");
        }
        List<JSONObject> result = pointsService.spend(spend);

        return success(result);
    }




}
