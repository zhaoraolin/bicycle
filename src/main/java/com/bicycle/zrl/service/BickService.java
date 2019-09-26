package com.bicycle.zrl.service;

import com.bicycle.zrl.entity.Bick;
import org.springframework.data.geo.GeoResult;

import java.util.List;

public interface BickService {
    //添加单车
    void addBick(Bick bick);

    List<GeoResult<Bick>> findNear(double longitude, double latitude);
}
