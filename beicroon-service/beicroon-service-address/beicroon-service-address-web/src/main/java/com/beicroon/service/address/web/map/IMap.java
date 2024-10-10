package com.beicroon.service.address.web.map;

import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.vo.GeoVO;
import com.beicroon.service.address.web.vo.PoiVO;
import com.beicroon.service.address.web.vo.ReGeoVO;

public interface IMap {

    PoiVO poi(String city, String keywords);

    GeoVO geo(String city, String address);

    ReGeoVO reGeo(Coordinate coordinate);

}
