package com.beicroon.service.address.web.map.impl;

import com.beicroon.construct.entity.Coordinate;
import com.beicroon.construct.http.entity.Http;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.address.web.helper.DigestHelper;
import com.beicroon.service.address.web.map.IMap;
import com.beicroon.service.address.web.property.impl.TengxunProperty;
import com.beicroon.service.address.web.response.tengxun.TengxunGeoResponse;
import com.beicroon.service.address.web.response.tengxun.TengxunPoiResponse;
import com.beicroon.service.address.web.response.tengxun.TengxunReGeoResponse;
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
            query.put("sig", DigestHelper.tencent(this.property.getPoiUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getPoiUrl(), query);

        return http.getResponseAs(TengxunPoiResponse.class).getPoi();
    }

    @Override
    public GeoVO geo(String city, String address) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("address", address);
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.tencent(this.property.getGeoUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getGeoUrl(), query);

        return http.getResponseAs(TengxunGeoResponse.class).getGeo();
    }

    @Override
    public ReGeoVO reGeo(Coordinate coordinate) {
        Map<String, String> query = new LinkedHashMap<>();

        query.put("key", this.property.getKey());
        query.put("location", coordinate.toLatLngString());
        query.put("output", "json");

        if (EmptyUtils.isNotEmpty(this.property.getSecret())) {
            query.put("sig", DigestHelper.tencent(this.property.getReGeoUri(), query, this.property.getSecret()));
        }

        Http http = HttpUtils.get(this.property.getReGeoUrl(), query);

        return http.getResponseAs(TengxunReGeoResponse.class).getReGeo(coordinate);
    }

}
