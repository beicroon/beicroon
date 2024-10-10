package com.beicroon.service.address.web.response.gaode;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.deserializer.StringOrArrayDeserializer;
import com.beicroon.service.address.web.response.BaseReGeoResponse;
import com.beicroon.service.address.web.vo.ReGeoVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodeReGeoResponse extends BaseReGeoResponse {

    @ApiModelProperty(name = "返回结果状态值")
    private Integer status;

    @ApiModelProperty(name = "返回状态说明")
    private String info;

    @ApiModelProperty(name = "逆地理编码列表")
    private ReGeoCode regeocode;

    @Override
    public ReGeoVO getReGeo(Coordinate coordinate) {
        if (this.regeocode == null || this.regeocode.getAddressComponent() == null) {
            return null;
        }

        ReGeoVO res = new ReGeoVO();

        res.setProvince(this.regeocode.getAddressComponent().getProvince());
        res.setCity(this.regeocode.getAddressComponent().getCity());
        res.setDistrict(this.regeocode.getAddressComponent().getDistrict());
        res.setStreet(this.regeocode.getAddressComponent().getTownship());
        res.setAddress(this.regeocode.getFormattedAddress());

        return res;
    }

    @Data
    public static class ReGeoCode implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "地址元素列表")
        private AddressComponent addressComponent;

        @JsonAlias(value = "formatted_address")
        @ApiModelProperty(name = "反解析地址")
        private String formattedAddress;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "坐标点所在国家名称")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String country;

        @ApiModelProperty(name = "坐标点所在省名称")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String province;

        @ApiModelProperty(name = "坐标点所在城市名称")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String city;

        @ApiModelProperty(name = "坐标点所在区")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String district;

        @ApiModelProperty(name = "坐标点所在乡镇/街道（此街道为社区街道，不是道路信息）")
        @JsonDeserialize(using = StringOrArrayDeserializer.class)
        private String township;

    }

}
