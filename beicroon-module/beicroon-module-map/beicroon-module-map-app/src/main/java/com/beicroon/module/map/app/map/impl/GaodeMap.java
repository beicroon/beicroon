package com.beicroon.module.map.app.map.impl;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.StringUtils;
import com.beicroon.module.map.app.helper.DigestHelper;
import com.beicroon.module.map.app.map.IMap;
import com.beicroon.module.map.app.property.impl.GaodeProperty;
import com.beicroon.module.map.app.response.gaode.GaodeDirectionDrivingResponse;
import com.beicroon.module.map.app.response.gaode.GaodeGeoResponse;
import com.beicroon.module.map.app.response.gaode.GaodePoiResponse;
import com.beicroon.module.map.app.response.gaode.GaodeReGeoResponse;
import com.beicroon.module.map.entity.vo.DirectionVO;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.entity.vo.ReGeoVO;
import com.beicroon.module.map.enums.CarTypeEnums;
import com.beicroon.module.map.enums.DirectionStrategyEnums;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Component
public class GaodeMap implements IMap {

    @Resource
    private GaodeProperty property;

    @Override
    public PoiVO poi(String city, String keywords) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("region", city);
        query.put("keywords", keywords);
        query.put("city_limit", "true");
        query.put("page_num", "1");
        query.put("page_size", "10");
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.gaode(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        GaodePoiResponse res = http.getResponseAs(GaodePoiResponse.class);

        return res.getPoi();
    }

    @Override
    public GeoVO geo(String city, String address) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("city", city);
        query.put("address", address);
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.gaode(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        GaodeGeoResponse res = http.getResponseAs(GaodeGeoResponse.class);

        return res.getGeo();
    }

    @Override
    public ReGeoVO reGeo(Coordinate coordinate) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("location", coordinate.toLngLatString());
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.gaode(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        GaodeReGeoResponse res = http.getResponseAs(GaodeReGeoResponse.class);

        return res.getReGeo(coordinate);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination) {
        return this.driving(origin, destination, EmptyUtils.emptyList(), DirectionStrategyEnums.DEFAULT, CarTypeEnums.FUEL);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy) {
        return this.driving(origin, destination, EmptyUtils.emptyList(), strategy, CarTypeEnums.FUEL);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy, CarTypeEnums carType) {
        return this.driving(origin, destination, EmptyUtils.emptyList(), DirectionStrategyEnums.DEFAULT, CarTypeEnums.FUEL);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints) {
        return this.driving(origin, destination, waypoints, DirectionStrategyEnums.DEFAULT, CarTypeEnums.FUEL);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy) {
        return this.driving(origin, destination, waypoints, strategy, CarTypeEnums.FUEL);
    }

    @Override
    public DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy, CarTypeEnums carType) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("origin", origin.toLngLatString());
        query.put("destination", destination.toLngLatString());
        query.put("strategy", strategy.getGaode());
        query.put("cartype", carType.getGaode());
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(waypoints)) {
            query.put("waypoints", StringUtils.join(ListUtils.toList(waypoints, Coordinate::toLngLatString), ";"));
        }

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.gaode(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getDrivingUrl(), query);

        GaodeDirectionDrivingResponse res = http.getResponseAs(GaodeDirectionDrivingResponse.class);

        return res.getDirection();
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
