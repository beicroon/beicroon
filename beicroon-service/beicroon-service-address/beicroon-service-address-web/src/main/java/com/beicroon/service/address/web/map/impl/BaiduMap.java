package com.beicroon.service.address.web.map.impl;

import com.beicroon.construct.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.TimeUtils;
import com.beicroon.service.address.web.helper.DigestHelper;
import com.beicroon.service.address.web.map.IMap;
import com.beicroon.service.address.web.property.impl.BaiduProperty;
import com.beicroon.service.address.web.response.baidu.BaiduGeoResponse;
import com.beicroon.service.address.web.response.baidu.BaiduPoiResponse;
import com.beicroon.service.address.web.response.baidu.BaiduReGeoResponse;
import com.beicroon.service.address.web.vo.GeoVO;
import com.beicroon.service.address.web.vo.PoiVO;
import com.beicroon.service.address.web.vo.ReGeoVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
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
            query.put("sn", DigestHelper.baidu(this.property.getPoiUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        return http.getResponseAs(BaiduPoiResponse.class).getPoi();
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
            query.put("sn", DigestHelper.baidu(this.property.getGeoUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        return http.getResponseAs(BaiduGeoResponse.class).getGeo();
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
            query.put("sn", DigestHelper.baidu(this.property.getReGeoUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        return http.getResponseAs(BaiduReGeoResponse.class).getReGeo(coordinate);
    }

}
