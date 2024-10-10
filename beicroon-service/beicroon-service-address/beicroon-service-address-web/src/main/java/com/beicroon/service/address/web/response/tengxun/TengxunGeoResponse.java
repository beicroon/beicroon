package com.beicroon.service.address.web.response.tengxun;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.response.BaseGeoResponse;
import com.beicroon.service.address.web.vo.GeoVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TengxunGeoResponse extends BaseGeoResponse {

    @ApiModelProperty(name = "状态码，0为正常，其它为异常")
    private Integer status;

    @ApiModelProperty(name = "状态说明")
    private String message;

    @ApiModelProperty(name = "地址解析结果")
    private Result result;

    @Override
    public GeoVO getGeo() {
        if (this.result == null || this.result.getLocation() == null) {
            return null;
        }

        GeoVO res = new GeoVO();

        res.setCoordinate(this.result.getLocation());

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "最终用于坐标解析的地址或地点名称")
        private String title;

        @ApiModelProperty(name = "解析到的坐标（GCJ02坐标系）")
        private Coordinate location;

        @JsonAlias(value = "address_components")
        @ApiModelProperty(name = "解析后的地址部件")
        private AddressComponent addressComponent;

        @ApiModelProperty(name = "可信度参考：值范围 1 <低可信> - 10 <高可信>")
        private Integer reliability;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "地址所在的省份名")
        private String province;

        @ApiModelProperty(name = "地址所在的城市名")
        private String city;

        @ApiModelProperty(name = "地址所在的区")
        private String district;

        @ApiModelProperty(name = "街道/道路，可能为空字串")
        private String street;

        @JsonAlias(value = "street_number")
        @ApiModelProperty(name = "门牌，可能为空字串")
        private String streetNumber;

    }

}
