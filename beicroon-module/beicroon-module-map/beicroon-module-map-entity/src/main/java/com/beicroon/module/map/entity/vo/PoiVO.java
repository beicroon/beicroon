package com.beicroon.module.map.entity.vo;

import com.beicroon.construct.base.entity.BaseVO;
import com.beicroon.construct.base.entity.Coordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PoiVO extends BaseVO {

    @Schema(name = "mapType", description = "图商")
    private String mapType;

    @Schema(name = "name", description = "名称")
    private String name;

    @Schema(name = "province", description = "省份")
    private String province;

    @Schema(name = "city", description = "城市")
    private String city;

    @Schema(name = "district", description = "行政区")
    private String district;

    @Schema(name = "street", description = "街道")
    private String street;

    @Schema(name = "address", description = "详细地址")
    private String address;

    @Schema(name = "coordinate", description = "经纬度")
    private Coordinate coordinate;

}
