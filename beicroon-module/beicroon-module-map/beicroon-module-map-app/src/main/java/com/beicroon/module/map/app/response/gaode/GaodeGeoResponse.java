package com.beicroon.module.map.app.response.gaode;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.module.map.app.deserializer.StringOrArrayDeserializer;
import com.beicroon.module.map.app.response.BaseGeoResponse;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodeGeoResponse extends BaseGeoResponse {

    private static final List<String> LEVELS = Arrays.asList("国家", "省", "市", "区县", "开发区", "未知");

    @Schema(name = "status", description = "返回结果状态值")
    private Integer status;

    @Schema(name = "info", description = "返回状态说明")
    private String info;

    @Schema(name = "geocodes", description = "地理编码信息列表")
    private List<Geocode> geocodes;

    @Override
    public GeoVO getGeo() {
        if (EmptyUtils.isEmpty(this.geocodes)) {
            return null;
        }

        Geocode code = this.geocodes.parallelStream()
                .filter(geocode -> !GaodeGeoResponse.LEVELS.contains(geocode.getLevel()))
                .findFirst()
                .orElse(this.geocodes.get(0));

        String[] locations = code.getLocation().split(",", 2);

        GeoVO res = new GeoVO();

        res.setMapType(MapTypeEnums.GAODE.getCode());
        res.setCoordinate(new Coordinate(locations[0], locations[1]));

        return res;
    }

    @Data
    public static class Geocode implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonAlias(value = "formatted_address")
        @Schema(name = "address", description = "地址")
        private String address;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "country", description = "国家")
        private String country;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "province", description = "地址所在的省份名")
        private String province;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "city", description = "地址所在的城市名")
        private String city;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "district", description = "地址所在的区")
        private String district;

        @Schema(name = "location", description = "坐标点")
        private String location;

        @Schema(name = "level", description = "匹配级别")
        private String level;

    }

}
