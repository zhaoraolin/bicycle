package com.bicycle.zrl.service.impl;

import com.bicycle.zrl.entity.Bick;
import com.bicycle.zrl.service.BickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BickServiceImpl implements BickService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addBick(Bick bick) {
        mongoTemplate.insert(bick);
    }

    /**
     * 查找附近的单车
     * @param longitude   经度
     * @param latitude    纬度
     * @return
     */
    @Override
    public List<GeoResult<Bick>> findNear(double longitude,double latitude) {
        //查找所有的单车
      //  List<Bick> list = mongoTemplate.findAll(Bick.class);
        NearQuery near = NearQuery.near(longitude,latitude)
                        .maxDistance(0.2, Metrics.KILOMETERS)      //查找的范围和距离单位   0.2km
                        .query(new Query(Criteria.where("status").is(0)).limit(100));  //查询条件,以及默认查询多少条数据
        GeoResults<Bick> geoResults = mongoTemplate.geoNear(near, Bick.class);
        List<GeoResult<Bick>> list = geoResults.getContent();
        return list;
    }

}
