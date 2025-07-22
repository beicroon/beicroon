package com.beicroon.module.map.app.map.impl;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.TimeUtils;
import com.beicroon.module.map.app.helper.DigestHelper;
import com.beicroon.module.map.app.map.IMap;
import com.beicroon.module.map.app.property.impl.BaiduProperty;
import com.beicroon.module.map.app.response.baidu.BaiduGeoResponse;
import com.beicroon.module.map.app.response.baidu.BaiduPoiResponse;
import com.beicroon.module.map.app.response.baidu.BaiduReGeoResponse;
import com.beicroon.module.map.entity.vo.DirectionVO;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.entity.vo.ReGeoVO;
import com.beicroon.module.map.enums.CarTypeEnums;
import com.beicroon.module.map.enums.DirectionStrategyEnums;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BaiduMap implements IMap {

    @Resource
    private BaiduProperty property;

    @Override
    public PoiVO poi(String city, String keywords) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("ak", this.property.getKey());
        query.put("q", keywords);
        query.put("region", city);
        query.put("city_limit", "true");
        query.put("ret_coordtype", "gcj02");
        query.put("output", "json");
        query.put("timestamp", String.valueOf(TimeUtils.currentTimestamp()));

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sn", DigestHelper.baidu(this.property.getPoiPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        BaiduPoiResponse res = http.getResponseAs(BaiduPoiResponse.class);

        return res.getPoi();
    }

    @Override
    public GeoVO geo(String city, String address) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("ak", this.property.getKey());
        query.put("city", city);
        query.put("address", address);
        query.put("ret_coordtype", "gcj02");
        query.put("output", "json");
        query.put("extension_analys_level", "true");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sn", DigestHelper.baidu(this.property.getGeoPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        BaiduGeoResponse res = http.getResponseAs(BaiduGeoResponse.class);

        return res.getGeo();
    }

    @Override
    public ReGeoVO reGeo(Coordinate coordinate) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("ak", this.property.getKey());
        query.put("location", coordinate.toLatLngString());
        query.put("coordtype", "gcj02");
        query.put("ret_coordtype", "gcj02");
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sn", DigestHelper.baidu(this.property.getReGeoPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        BaiduReGeoResponse res = http.getResponseAs(BaiduReGeoResponse.class);

        return res.getReGeo(coordinate);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination) {
        return null;
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy) {
        return null;
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy, CarTypeEnums carType) {
        return null;
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints) {
        return null;
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy) {
        return null;
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy, CarTypeEnums carType) {
        return null;
    }

    @Override
    public DirectionVO walking(Coordinate origin, Coordinate destination) {
        return null;
    }

    @Override
    public DirectionVO walking(Coordinate origin, Coordinate destination, List<Coordinate> waypoints) {
        return null;
    }

    @Override
    public DirectionVO bicycling(Coordinate origin, Coordinate destination) {
        return null;
    }

    @Override
    public DirectionVO bicycling(Coordinate origin, Coordinate destination, List<Coordinate> waypoints) {
        return null;
    }

    @Override
    public DirectionVO electrobike(Coordinate origin, Coordinate destination) {
        return null;
    }

    @Override
    public DirectionVO electrobike(Coordinate origin, Coordinate destination, List<Coordinate> waypoints) {
        return null;
    }

}
