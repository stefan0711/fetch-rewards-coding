package com.zhip.fetchrewardscoding.service;
import com.alibaba.fastjson.JSONObject;
import com.zhip.fetchrewardscoding.model.PointsRecords;
import com.zhip.fetchrewardscoding.model.Spend;

import java.util.List;

/**
 * @author Zhipeng Yin

 */

public interface PointsService {
    JSONObject getAll();

    void add(PointsRecords pointsRecords);

    boolean checkExistPayer(PointsRecords pointsRecords);

    List<JSONObject> spend(Spend spend);


}
