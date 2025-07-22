package com.beicroon.module.map.app.response.gaode;

import com.beicroon.module.map.app.deserializer.StringOrArrayDeserializer;
import com.beicroon.module.map.app.response.BasePoiResponse;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodePoiResponse extends BasePoiResponse {

    @Schema(name = "status", description = "返回结果状态值")
    private Integer status;

    @Schema(name = "info", description = "返回状态说明")
    private String info;

    @Schema(name = "pois", description = "返回的poi完整集合")
    private List<Poi> pois;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        res.setMapType(MapTypeEnums.GAODE.getCode());

        return res;
    }

    @Data
    public static class Poi implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "name", description = "poi名称")
        private String name;

        @Schema(name = "location", description = "坐标点")
        private String location;

        @JsonAlias(value = "pname")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "province", description = "poi所属省份")
        private String province;

        @JsonAlias(value = "cityname")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "city", description = "poi所属城市")
        private String city;

        @JsonAlias(value = "adname")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "district", description = "poi所属区县")
        private String district;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "address", description = "poi详细地址")
        private String address;

    }

}
