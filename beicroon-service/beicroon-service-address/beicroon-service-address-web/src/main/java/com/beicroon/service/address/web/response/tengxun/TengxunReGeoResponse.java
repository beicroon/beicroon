package com.beicroon.service.address.web.response.tengxun;

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
public class TengxunReGeoResponse extends BaseReGeoResponse {

    @ApiModelProperty(name = "状态码，0为正常，其它为异常")
    private Integer status;

    @ApiModelProperty(name = "状态说明")
    private String message;

    @ApiModelProperty(name = "逆地址解析结果")
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

        @ApiModelProperty(name = "以行政区划+道路+门牌号等信息组成的标准格式化地址")
        private String address;

        @JsonAlias(value = "address_component")
        @ApiModelProperty(name = "地址部件，address不满足需求时可自行拼接")
        private AddressComponent addressComponent;

    }

    @Data
    public static class AddressComponent implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "省")
        private String province;

        @ApiModelProperty(name = "市，如果当前城市为省直辖县级区划，city与district字段均会返回此城市")
        private String city;

        @ApiModelProperty(name = "区")
        private String district;

        @ApiModelProperty(name = "街道")
        private String street;

    }

}
