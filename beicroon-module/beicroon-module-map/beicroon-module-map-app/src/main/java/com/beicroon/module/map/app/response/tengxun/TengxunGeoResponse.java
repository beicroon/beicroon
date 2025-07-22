package com.beicroon.module.map.app.response.tengxun;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.response.BaseGeoResponse;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TengxunGeoResponse extends BaseGeoResponse {

    @Schema(name = "status", description = "状态码，0为正常，其它为异常")
    private Integer status;

    @Schema(name = "message", description = "状态说明")
    private String message;

    @Schema(name = "result", description = "地址解析结果")
    private Result result;

    @Override
    public GeoVO getGeo() {
        if (this.result == null || this.result.getLocation() == null) {
            return null;
        }

        GeoVO res = new GeoVO();

        res.setMapType(MapTypeEnums.TENGXUN.getCode());
        res.setCoordinate(this.result.getLocation());

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "title", description = "最终用于坐标解析的地址或地点名称")
        private String title;

        @Schema(name = "location", description = "解析到的坐标（GCJ02坐标系）")
        private Coordinate location;

        @JsonAlias(value = "address_components")
        @Schema(name = "addressComponent", description = "解析后的地址部件")
        private AddressComponent addressComponent;

        @Schema(name = "reliability", description = "可信度参考：值范围 1 <低可信> - 10 <高可信>")
        private Integer reliability;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "province", description = "地址所在的省份名")
        private String province;

        @Schema(name = "city", description = "地址所在的城市名")
        private String city;

        @Schema(name = "district", description = "地址所在的区")
        private String district;

        @Schema(name = "street", description = "街道/道路，可能为空字串")
        private String street;

        @JsonAlias(value = "street_number")
        @Schema(name = "streetNumber", description = "门牌，可能为空字串")
        private String streetNumber;

    }

}
