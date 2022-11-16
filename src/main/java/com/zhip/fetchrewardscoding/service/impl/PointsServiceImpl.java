package com.zhip.fetchrewardscoding.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhip.fetchrewardscoding.data.DatabaseInit;
import com.zhip.fetchrewardscoding.model.PointsRecords;
import com.zhip.fetchrewardscoding.model.Spend;
import com.zhip.fetchrewardscoding.service.PointsService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

/**
 * @author Zhipeng Yin
 */
@Service("pointsService")
public class PointsServiceImpl implements PointsService {

     List<JSONObject> transaction = DatabaseInit.transactionDatabase.transaction;

    @Override
    public JSONObject getAll() {
        JSONObject result = new JSONObject();
        if(transaction == null){
            result.put("message","No current records!");
            return result;
        }
        for (JSONObject item:transaction) {
            String payer = item.getString("payer");
            if(result.getInteger(payer) == null){
                result.put(payer,0);
            }
            result.put(payer,result.getInteger(payer) + item.getInteger("points"));

        }
        return result;
    }

    @Override
    public void add(PointsRecords pointsRecords) {

        JSONObject newRecords = new JSONObject();
        newRecords.put("payer",pointsRecords.getPayer());
        newRecords.put("points",pointsRecords.getPoints());
        newRecords.put("timestamp",pointsRecords.getTimestamp());
        transaction.add(newRecords);

    }

    @Override
    public boolean checkExistPayer(PointsRecords pointsRecords) {
        for (JSONObject item:transaction) {
            if(item.containsValue(pointsRecords.getPayer())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<JSONObject> spend(Spend spend) {
        List<JSONObject> trans = transaction;
        //Sort by timestamp, the earlier the transaction is listed first
        trans.sort(Comparator.comparing(t -> t.getDate("timestamp")));
        Iterator<JSONObject> iterator = trans.iterator();
        Map<String,Integer> spending = new HashMap<>();
        Integer  toSpendPoints = spend.getPoints();

        while (toSpendPoints > 0){
            JSONObject item = iterator.next();
            String payer = item.getString("payer");
            Integer itemPoints = item.getInteger("points");
            Integer spendingPoints = spending.getOrDefault(payer,0);
            if(spend.getPoints() >= itemPoints){
                spending.put(payer,spendingPoints + itemPoints);
                item.put("points",0);

                toSpendPoints = toSpendPoints - itemPoints;
            }else {
                spending.put(payer,spendingPoints + toSpendPoints);

                item.put("points",itemPoints - toSpendPoints);

                toSpendPoints = 0;
            }
        }

        List<JSONObject> res = new ArrayList<>();
        for (String key:spending.keySet()) {
            JSONObject spendInfo = new JSONObject();
            spendInfo.put(key,spending.get(key) * -1);
            res.add(spendInfo);
        }
        return res;
    }
}
