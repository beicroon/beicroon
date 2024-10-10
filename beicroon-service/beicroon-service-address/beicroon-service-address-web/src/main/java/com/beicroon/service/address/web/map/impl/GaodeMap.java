package com.beicroon.service.address.web.map.impl;

import com.beicroon.construct.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.address.web.helper.DigestHelper;
import com.beicroon.service.address.web.map.IMap;
import com.beicroon.service.address.web.property.impl.GaodeProperty;
import com.beicroon.service.address.web.response.gaode.GaodeGeoResponse;
import com.beicroon.service.address.web.response.gaode.GaodePoiResponse;
import com.beicroon.service.address.web.response.gaode.GaodeReGeoResponse;
import com.beicroon.service.address.web.vo.GeoVO;
import com.beicroon.service.address.web.vo.PoiVO;
import com.beicroon.service.address.web.vo.ReGeoVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
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
            query.put("sig", DigestHelper.aMap(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        return http.getResponseAs(GaodePoiResponse.class).getPoi();
    }

    @Override
    public GeoVO geo(String city, String address) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("city", city);
        query.put("address", address);
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.aMap(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        return http.getResponseAs(GaodeGeoResponse.class).getGeo();
    }

    @Override
    public ReGeoVO reGeo(Coordinate coordinate) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("location", coordinate.toLngLatString());
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.aMap(query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        return http.getResponseAs(GaodeReGeoResponse.class).getReGeo(coordinate);
    }

}
