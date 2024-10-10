package com.beicroon.service.address.web.response.gaode;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.service.address.web.deserializer.StringOrArrayDeserializer;
import com.beicroon.service.address.web.response.BasePoiResponse;
import com.beicroon.service.address.web.vo.PoiVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodePoiResponse extends BasePoiResponse {

    @ApiModelProperty(name = "返回结果状态值")
    private Integer status;

    @ApiModelProperty(name = "返回状态说明")
    private String info;

    @ApiModelProperty(name = "返回的poi完整集合")
    private List<Poi> pois;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        return res;
    }

    @Data
    public static class Poi implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "poi名称")
        private String name;

        @ApiModelProperty(name = "坐标点")
        private String location;

        @JsonAlias(value = "pname")
        @ApiModelProperty(name = "poi所属省份")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String province;

        @JsonAlias(value = "cityname")
        @ApiModelProperty(name = "poi所属城市")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String city;

        @JsonAlias(value = "adname")
        @ApiModelProperty(name = "poi所属区县")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String district;

        @ApiModelProperty(name = "poi详细地址")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String address;

    }

}
