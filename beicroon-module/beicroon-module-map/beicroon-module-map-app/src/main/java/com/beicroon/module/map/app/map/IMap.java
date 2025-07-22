package com.beicroon.module.map.app.map;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.entity.vo.DirectionVO;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.entity.vo.ReGeoVO;
import com.beicroon.module.map.enums.CarTypeEnums;
import com.beicroon.module.map.enums.DirectionStrategyEnums;

import java.util.List;

public interface IMap {

    PoiVO poi(String city, String keywords);

    GeoVO geo(String city, String address);

    ReGeoVO reGeo(Coordinate coordinate);

    DirectionVO driving(Coordinate origin, Coordinate destination);

    DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy);

    DirectionVO driving(Coordinate origin, Coordinate destination, DirectionStrategyEnums strategy, CarTypeEnums carType);

    DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints);

    DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy);

    DirectionVO driving(Coordinate origin, Coordinate destination, List<Coordinate> waypoints, DirectionStrategyEnums strategy, CarTypeEnums carType);

    DirectionVO walking(Coordinate origin, Coordinate destination);

    DirectionVO walking(Coordinate origin, Coordinate destination, List<Coordinate> waypoints);

    DirectionVO bicycling(Coordinate origin, Coordinate destination);

    DirectionVO bicycling(Coordinate origin, Coordinate destination, List<Coordinate> waypoints);

    DirectionVO electrobike(Coordinate origin, Coordinate destination);

    DirectionVO electrobike(Coordinate origin, Coordinate destination, List<Coordinate> waypoints);

}
