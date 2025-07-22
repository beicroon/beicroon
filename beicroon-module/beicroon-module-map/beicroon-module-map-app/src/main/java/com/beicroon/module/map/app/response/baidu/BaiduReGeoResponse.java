package com.beicroon.module.map.app.response.baidu;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.response.BaseReGeoResponse;
import com.beicroon.module.map.entity.vo.ReGeoVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduReGeoResponse extends BaseReGeoResponse {

    @Schema(name = "status", description = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @Schema(name = "result", description = "返回的结果")
    private Result result;

    @Override
    public ReGeoVO getReGeo(Coordinate coordinate) {
        if (this.result == null || this.result.getAddressComponent() == null) {
            return null;
        }

        ReGeoVO res = new ReGeoVO();

        res.setMapType(MapTypeEnums.BAIDU.getCode());
        res.setProvince(this.result.getAddressComponent().getProvince());
        res.setCity(this.result.getAddressComponent().getCity());
        res.setDistrict(this.result.getAddressComponent().getDistrict());
        res.setStreet(this.result.getAddressComponent().getStreet());
        res.setCoordinate(coordinate);

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "location", description = "poi经纬度坐标")
        private Coordinate location;

        @JsonAlias(value = "formatted_address")
        @Schema(name = "formattedAddress", description = "结构化地址信息")
        private String formattedAddress;

        @Schema(name = "addressComponent", description = "地址信息")
        private AddressComponent addressComponent;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "country", description = "国家")
        private String country;

        @Schema(name = "province", description = "省名")
        private String province;

        @Schema(name = "city", description = "城市名")
        private String city;

        @Schema(name = "district", description = "区县名")
        private String district;

        @Schema(name = "street", description = "道路名")
        private String street;

    }

}
