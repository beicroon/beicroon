package com.beicroon.service.address.entity.map.result.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "修改图商解析结果入参")
@EqualsAndHashCode(callSuper = true)
public class MapResultUpdateDTO extends UpdateDTO {
    
    @ApiModelProperty(name = "原地址")
    private String originAddress;

    @ApiModelProperty(name = "原地址CRC32值")
    private Long originAddressHash;

    @ApiModelProperty(name = "高德省份")
    private String gaodeProvince;

    @ApiModelProperty(name = "高德城市")
    private String gaodeCity;

    @ApiModelProperty(name = "高德区划")
    private String gaodeDistrict;

    @ApiModelProperty(name = "高德街道")
    private String gaodeStreet;

    @ApiModelProperty(name = "高德地址")
    private String gaodeAddress;

    @ApiModelProperty(name = "高德地址经度")
    private BigDecimal gaodeAddressLng;

    @ApiModelProperty(name = "高德地址纬度")
    private BigDecimal gaodeAddressLat;

    @ApiModelProperty(name = "腾讯省份")
    private String tengxunProvince;

    @ApiModelProperty(name = "腾讯城市")
    private String tengxunCity;

    @ApiModelProperty(name = "腾讯区划")
    private String tengxunDistrict;

    @ApiModelProperty(name = "腾讯街道")
    private String tengxunStreet;

    @ApiModelProperty(name = "腾讯地址")
    private String tengxunAddress;

    @ApiModelProperty(name = "腾讯地址经度")
    private BigDecimal tengxunAddressLng;

    @ApiModelProperty(name = "腾讯地址纬度")
    private BigDecimal tengxunAddressLat;

    @ApiModelProperty(name = "百度省份")
    private String baiduProvince;

    @ApiModelProperty(name = "百度城市")
    private String baiduCity;

    @ApiModelProperty(name = "百度区划")
    private String baiduDistrict;

    @ApiModelProperty(name = "百度街道")
    private String baiduStreet;

    @ApiModelProperty(name = "百度地址")
    private String baiduAddress;

    @ApiModelProperty(name = "百度地址经度")
    private BigDecimal baiduAddressLng;

    @ApiModelProperty(name = "百度地址纬度")
    private BigDecimal baiduAddressLat;

}
