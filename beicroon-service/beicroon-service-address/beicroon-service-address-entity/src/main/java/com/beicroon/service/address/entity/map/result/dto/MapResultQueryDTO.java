package com.beicroon.service.address.entity.map.result.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "查询图商解析结果入参")
@EqualsAndHashCode(callSuper = true)
public class MapResultQueryDTO extends QueryDTO {
    
    @FieldSearch(name = "`origin_address`")
    @ApiModelProperty(name = "原地址")
    private String originAddress;

    @FieldSearch(name = "`origin_address_hash`")
    @ApiModelProperty(name = "原地址CRC32值")
    private Long originAddressHash;

    @FieldSearch(name = "`gaode_province`")
    @ApiModelProperty(name = "高德省份")
    private String gaodeProvince;

    @FieldSearch(name = "`gaode_city`")
    @ApiModelProperty(name = "高德城市")
    private String gaodeCity;

    @FieldSearch(name = "`gaode_district`")
    @ApiModelProperty(name = "高德区划")
    private String gaodeDistrict;

    @FieldSearch(name = "`gaode_street`")
    @ApiModelProperty(name = "高德街道")
    private String gaodeStreet;

    @FieldSearch(name = "`gaode_address`")
    @ApiModelProperty(name = "高德地址")
    private String gaodeAddress;

    @FieldSearch(name = "`gaode_address_lng`")
    @ApiModelProperty(name = "高德地址经度")
    private BigDecimal gaodeAddressLng;

    @FieldSearch(name = "`gaode_address_lat`")
    @ApiModelProperty(name = "高德地址纬度")
    private BigDecimal gaodeAddressLat;

    @FieldSearch(name = "`tengxun_province`")
    @ApiModelProperty(name = "腾讯省份")
    private String tengxunProvince;

    @FieldSearch(name = "`tengxun_city`")
    @ApiModelProperty(name = "腾讯城市")
    private String tengxunCity;

    @FieldSearch(name = "`tengxun_district`")
    @ApiModelProperty(name = "腾讯区划")
    private String tengxunDistrict;

    @FieldSearch(name = "`tengxun_street`")
    @ApiModelProperty(name = "腾讯街道")
    private String tengxunStreet;

    @FieldSearch(name = "`tengxun_address`")
    @ApiModelProperty(name = "腾讯地址")
    private String tengxunAddress;

    @FieldSearch(name = "`tengxun_address_lng`")
    @ApiModelProperty(name = "腾讯地址经度")
    private BigDecimal tengxunAddressLng;

    @FieldSearch(name = "`tengxun_address_lat`")
    @ApiModelProperty(name = "腾讯地址纬度")
    private BigDecimal tengxunAddressLat;

    @FieldSearch(name = "`baidu_province`")
    @ApiModelProperty(name = "百度省份")
    private String baiduProvince;

    @FieldSearch(name = "`baidu_city`")
    @ApiModelProperty(name = "百度城市")
    private String baiduCity;

    @FieldSearch(name = "`baidu_district`")
    @ApiModelProperty(name = "百度区划")
    private String baiduDistrict;

    @FieldSearch(name = "`baidu_street`")
    @ApiModelProperty(name = "百度街道")
    private String baiduStreet;

    @FieldSearch(name = "`baidu_address`")
    @ApiModelProperty(name = "百度地址")
    private String baiduAddress;

    @FieldSearch(name = "`baidu_address_lng`")
    @ApiModelProperty(name = "百度地址经度")
    private BigDecimal baiduAddressLng;

    @FieldSearch(name = "`baidu_address_lat`")
    @ApiModelProperty(name = "百度地址纬度")
    private BigDecimal baiduAddressLat;

}
