package com.beicroon.service.address.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "图商解析结果")
@TableName("`address_map_result`")
@EqualsAndHashCode(callSuper = true)
public class MapResultModel extends GenericModel {
    
    @TableField(value = "`origin_address`")
    @ApiModelProperty(name = "原地址")
    private String originAddress;

    @TableField(value = "`origin_address_hash`")
    @ApiModelProperty(name = "原地址CRC32值")
    private Long originAddressHash;

    @TableField(value = "`gaode_province`")
    @ApiModelProperty(name = "高德省份")
    private String gaodeProvince;

    @TableField(value = "`gaode_city`")
    @ApiModelProperty(name = "高德城市")
    private String gaodeCity;

    @TableField(value = "`gaode_district`")
    @ApiModelProperty(name = "高德区划")
    private String gaodeDistrict;

    @TableField(value = "`gaode_street`")
    @ApiModelProperty(name = "高德街道")
    private String gaodeStreet;

    @TableField(value = "`gaode_address`")
    @ApiModelProperty(name = "高德地址")
    private String gaodeAddress;

    @TableField(value = "`gaode_address_lng`")
    @ApiModelProperty(name = "高德地址经度")
    private BigDecimal gaodeAddressLng;

    @TableField(value = "`gaode_address_lat`")
    @ApiModelProperty(name = "高德地址纬度")
    private BigDecimal gaodeAddressLat;

    @TableField(value = "`tengxun_province`")
    @ApiModelProperty(name = "腾讯省份")
    private String tengxunProvince;

    @TableField(value = "`tengxun_city`")
    @ApiModelProperty(name = "腾讯城市")
    private String tengxunCity;

    @TableField(value = "`tengxun_district`")
    @ApiModelProperty(name = "腾讯区划")
    private String tengxunDistrict;

    @TableField(value = "`tengxun_street`")
    @ApiModelProperty(name = "腾讯街道")
    private String tengxunStreet;

    @TableField(value = "`tengxun_address`")
    @ApiModelProperty(name = "腾讯地址")
    private String tengxunAddress;

    @TableField(value = "`tengxun_address_lng`")
    @ApiModelProperty(name = "腾讯地址经度")
    private BigDecimal tengxunAddressLng;

    @TableField(value = "`tengxun_address_lat`")
    @ApiModelProperty(name = "腾讯地址纬度")
    private BigDecimal tengxunAddressLat;

    @TableField(value = "`baidu_province`")
    @ApiModelProperty(name = "百度省份")
    private String baiduProvince;

    @TableField(value = "`baidu_city`")
    @ApiModelProperty(name = "百度城市")
    private String baiduCity;

    @TableField(value = "`baidu_district`")
    @ApiModelProperty(name = "百度区划")
    private String baiduDistrict;

    @TableField(value = "`baidu_street`")
    @ApiModelProperty(name = "百度街道")
    private String baiduStreet;

    @TableField(value = "`baidu_address`")
    @ApiModelProperty(name = "百度地址")
    private String baiduAddress;

    @TableField(value = "`baidu_address_lng`")
    @ApiModelProperty(name = "百度地址经度")
    private BigDecimal baiduAddressLng;

    @TableField(value = "`baidu_address_lat`")
    @ApiModelProperty(name = "百度地址纬度")
    private BigDecimal baiduAddressLat;

}
