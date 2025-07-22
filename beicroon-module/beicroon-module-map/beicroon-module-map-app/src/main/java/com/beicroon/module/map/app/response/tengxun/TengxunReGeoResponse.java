package com.beicroon.module.map.app.response.tengxun;

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
public class TengxunReGeoResponse extends BaseReGeoResponse {

    @Schema(name = "status", description = "状态码，0为正常，其它为异常")
    private Integer status;

    @Schema(name = "message", description = "状态说明")
    private String message;

    @Schema(name = "result", description = "逆地址解析结果")
    private Result result;

    @Override
    public ReGeoVO getReGeo(Coordinate coordinate) {
        if (this.result == null || this.result.getAddressComponent() == null) {
            return null;
        }

        ReGeoVO res = new ReGeoVO();

        res.setMapType(MapTypeEnums.TENGXUN.getCode());
        res.setCoordinate(coordinate);
        res.setProvince(this.result.getAddressComponent().getProvince());
        res.setCity(this.result.getAddressComponent().getCity());
        res.setDistrict(this.result.getAddressComponent().getDistrict());
        res.setStreet(this.result.getAddressComponent().getStreet());

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "address", description = "以行政区划+道路+门牌号等信息组成的标准格式化地址")
        private String address;

        @JsonAlias(value = "address_component")
        @Schema(name = "addressComponent", description = "地址部件，address不满足需求时可自行拼接")
        private AddressComponent addressComponent;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "nation", description = "国家")
        private String nation;

        @Schema(name = "province", description = "省")
        private String province;

        @Schema(name = "city", description = "市，如果当前城市为省直辖县级区划，city与district字段均会返回此城市")
        private String city;

        @Schema(name = "district", description = "区")
        private String district;

        @Schema(name = "street", description = "街道")
        private String street;

    }

}
