package com.beicroon.service.address.web.response.baidu;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.response.BaseReGeoResponse;
import com.beicroon.service.address.web.vo.ReGeoVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduReGeoResponse extends BaseReGeoResponse {

    @ApiModelProperty(name = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @ApiModelProperty(name = "返回的结果")
    private Result result;

    @Override
    public ReGeoVO getReGeo(Coordinate coordinate) {
        if (this.result == null || this.result.getAddressComponent() == null) {
            return null;
        }

        ReGeoVO res = new ReGeoVO();

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

        @ApiModelProperty(name = "poi经纬度坐标")
        private Coordinate location;

        @JsonAlias(value = "formatted_address")
        @ApiModelProperty(name = "结构化地址信息")
        private String formattedAddress;

        @ApiModelProperty(name = "地址信息")
        private AddressComponent addressComponent;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "省名")
        private String province;

        @ApiModelProperty(name = "城市名")
        private String city;

        @ApiModelProperty(name = "区县名")
        private String district;

        @ApiModelProperty(name = "道路名")
        private String street;

    }

}
