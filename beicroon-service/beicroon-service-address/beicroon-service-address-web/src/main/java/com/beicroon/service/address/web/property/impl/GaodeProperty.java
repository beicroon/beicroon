package com.beicroon.service.address.web.property.impl;

import com.beicroon.service.address.web.property.UrlProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class GaodeProperty implements UrlProperty {

    @Value("${map.company.amap.key}")
    private String key;

    @Value("${map.company.amap.secret:}")
    private String secret;

    @Value("${map.company.amap.host}")
    private String host;

    @Value("${map.company.amap.uri.poi}")
    private String poiUri;

    @Value("${map.company.amap.uri.geo}")
    private String geoUri;

    @Value("${map.company.amap.uri.regeo}")
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
