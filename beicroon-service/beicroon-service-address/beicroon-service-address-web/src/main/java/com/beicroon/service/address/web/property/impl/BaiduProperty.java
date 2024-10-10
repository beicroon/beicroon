package com.beicroon.service.address.web.property.impl;

import com.beicroon.service.address.web.property.UrlProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class BaiduProperty implements UrlProperty {

    @Value("${map.company.baidu.key}")
    private String key;

    @Value("${map.company.baidu.secret:}")
    private String secret;

    @Value("${map.company.baidu.host}")
    private String host;

    @Value("${map.company.baidu.uri.poi}")
    private String poiUri;

    @Value("${map.company.baidu.uri.geo}")
    private String geoUri;

    @Value("${map.company.baidu.uri.regeo}")
    private String reGeoUri;

    @Override
    public String getPoiUrl() {
        return String.format("%s%s", this.host, this.poiUri);
    }

    @Override
    public String getGeoUrl() {
        return String.format("%s%s", this.host, this.geoUri);
    }

    @Override
    public String getReGeoUrl() {
        return String.format("%s%s", this.host, this.reGeoUri);
    }

}
