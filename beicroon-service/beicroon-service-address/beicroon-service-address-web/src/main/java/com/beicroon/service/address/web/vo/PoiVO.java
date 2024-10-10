package com.beicroon.service.address.web.vo;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PoiVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "解析省份")
    private String province;

    @ApiModelProperty(name = "解析城市")
    private String city;

    @ApiModelProperty(name = "解析行政区")
    private String district;

    @ApiModelProperty(name = "解析街道")
    private String street;

    @ApiModelProperty(name = "解析地址")
    private String address;

    @ApiModelProperty(name = "解析坐标")
    private Coordinate coordinate;

}
