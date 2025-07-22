package com.beicroon.module.map.app.response.gaode;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.deserializer.StringOrArrayDeserializer;
import com.beicroon.module.map.app.response.BaseReGeoResponse;
import com.beicroon.module.map.entity.vo.ReGeoVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodeReGeoResponse extends BaseReGeoResponse {

    @Schema(name = "status", description = "返回结果状态值")
    private Integer status;

    @Schema(name = "info", description = "返回状态说明")
    private String info;

    @Schema(name = "regeocode", description = "逆地理编码列表")
    private ReGeoCode regeocode;

    @Override
    public ReGeoVO getReGeo(Coordinate coordinate) {
        if (this.regeocode == null || this.regeocode.getAddressComponent() == null) {
            return null;
        }

        ReGeoVO res = new ReGeoVO();

        res.setMapType(MapTypeEnums.GAODE.getCode());
        res.setProvince(this.regeocode.getAddressComponent().getProvince());
        res.setCity(this.regeocode.getAddressComponent().getCity());
        res.setDistrict(this.regeocode.getAddressComponent().getDistrict());
        res.setStreet(this.regeocode.getAddressComponent().getTownship());
        res.setAddress(this.regeocode.getFormattedAddress());
        res.setCoordinate(coordinate);

        return res;
    }

    @Data
    public static class ReGeoCode implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "addressComponent", description = "地址元素列表")
        private AddressComponent addressComponent;

        @JsonAlias(value = "formatted_address")
        @Schema(name = "formattedAddress", description = "反解析地址")
        private String formattedAddress;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "country", description = "坐标点所在国家名称")
        private String country;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "province", description = "坐标点所在省名称")
        private String province;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "city", description = "坐标点所在城市名称")
        private String city;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "citycode", description = "城市编码")
        private String citycode;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "district", description = "坐标点所在区")
        private String district;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "adcode", description = "行政区编码")
        private String adcode;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "township", description = "坐标点所在乡镇/街道（此街道为社区街道，不是道路信息）")
        private String township;

        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        @Schema(name = "towncode", description = "乡镇街道编码")
        private String towncode;

    }

}
