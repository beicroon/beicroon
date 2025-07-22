package com.beicroon.module.map.app.map.impl;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.module.map.app.helper.DigestHelper;
import com.beicroon.module.map.app.map.IMap;
import com.beicroon.module.map.app.property.impl.TengxunProperty;
import com.beicroon.module.map.app.response.tengxun.TengxunGeoResponse;
import com.beicroon.module.map.app.response.tengxun.TengxunPoiResponse;
import com.beicroon.module.map.app.response.tengxun.TengxunReGeoResponse;
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
public class TengxunMap implements IMap {

    @Resource
    private TengxunProperty property;

    @Override
    public PoiVO poi(String city, String keywords) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("region", city);
        query.put("keyword", keywords);
        query.put("region_fix", "1");
        query.put("policy", "1");
        query.put("address_format", "short");
        query.put("page_index", "1");
        query.put("page_size", "10");
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.tencent(this.property.getPoiPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        TengxunPoiResponse res = http.getResponseAs(TengxunPoiResponse.class);

        return res.getPoi();
    }

    @Override
    public GeoVO geo(String city, String address) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("address", address);
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.tencent(this.property.getGeoPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        TengxunGeoResponse res = http.getResponseAs(TengxunGeoResponse.class);

        return res.getGeo();
    }

    @Override
    public ReGeoVO reGeo(Coordinate coordinate) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("location", coordinate.toLatLngString());
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.tencent(this.property.getReGeoPath(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        TengxunReGeoResponse res = http.getResponseAs(TengxunReGeoResponse.class);

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
