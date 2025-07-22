package com.beicroon.module.map.app.property.impl;

import com.beicroon.module.map.app.property.UrlProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TengxunProperty implements UrlProperty {

    @Value("${map.company.tencent.key}")
    private String key;

    @Value("${map.company.tencent.secret:}")
    private String secret;

    @Value("${map.company.tencent.base-url}")
    private String baseUrl;

    @Value("${map.company.tencent.path.poi}")
    private String poiPath;

    @Value("${map.company.tencent.path.geo}")
    private String geoPath;

    @Value("${map.company.tencent.path.regeo}")
    private String reGeoPath;

    @Value("${map.company.tencent.path.driving}")
    private String drivingPath;

    @Value("${map.company.tencent.path.walking}")
    private String walkingPath;

    @Value("${map.company.tencent.path.bicycling}")
    private String bicyclingPath;

    @Value("${map.company.tencent.path.electrobike}")
    private String electrobikePath;

    @Override
    public String getPoiUrl() {
        return String.format("%s%s", this.baseUrl, this.poiPath);
    }

    @Override
    public String getGeoUrl() {
        return String.format("%s%s", this.baseUrl, this.geoPath);
    }

    @Override
    public String getReGeoUrl() {
        return String.format("%s%s", this.baseUrl, this.reGeoPath);
    }

    @Override
    public String getDrivingUrl() {
        return String.format("%s%s", this.baseUrl, this.drivingPath);
    }

    @Override
    public String getWalkingUrl() {
        return String.format("%s%s", this.baseUrl, this.walkingPath);
    }

    @Override
    public String getBicyclingUrl() {
        return String.format("%s%s", this.baseUrl, this.bicyclingPath);
    }

    @Override
    public String getElectrobikeUrl() {
        return String.format("%s%s", this.baseUrl, this.electrobikePath);
    }

}
