package com.beicroon.service.address.web.response.gaode;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.address.web.deserializer.StringOrArrayDeserializer;
import com.beicroon.service.address.web.response.BaseGeoResponse;
import com.beicroon.service.address.web.vo.GeoVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

    @ApiModelProperty(name = "返回结果状态值")
    private Integer status;

    @ApiModelProperty(name = "返回状态说明")
    private String info;

    @ApiModelProperty(name = "地理编码信息列表")
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

        res.setCoordinate(new Coordinate(locations[0], locations[1]));

        return res;
    }

    @Data
    public static class Geocode implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonAlias(value = "formatted_address")
        @ApiModelProperty(name = "地址")
        private String address;

        @ApiModelProperty(name = "国家")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String country;

        @ApiModelProperty(name = "地址所在的省份名")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String province;

        @ApiModelProperty(name = "地址所在的城市名")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String city;

        @ApiModelProperty(name = "地址所在的区")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String district;

        @ApiModelProperty(name = "坐标点")
        private String location;

        @ApiModelProperty(name = "匹配级别")
        private String level;

    }

}
