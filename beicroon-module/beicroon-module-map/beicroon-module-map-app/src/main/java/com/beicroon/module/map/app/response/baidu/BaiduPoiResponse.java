package com.beicroon.module.map.app.response.baidu;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.response.BasePoiResponse;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduPoiResponse extends BasePoiResponse {

    @Schema(name = "status", description = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @Schema(name = "message", description = "对API访问状态值的英文说明，如果成功返回ok，并返回结果字段，如果失败返回错误说明。")
    private String message;

    @Schema(name = "result", description = "返回的结果")
    private List<Result> result;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        res.setMapType(MapTypeEnums.BAIDU.getCode());

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "name", description = "poi名称，单次请求最多返回10条结果")
        private String name;

        @Schema(name = "location", description = "poi经纬度坐标")
        private Coordinate location;

        @Schema(name = "province", description = "所属省份")
        private String province;

        @Schema(name = "city", description = "所属城市")
        private String city;

        @Schema(name = "district", description = "区县")
        private String district;

        @Schema(name = "address", description = "poi地址")
        private String address;

    }

}
