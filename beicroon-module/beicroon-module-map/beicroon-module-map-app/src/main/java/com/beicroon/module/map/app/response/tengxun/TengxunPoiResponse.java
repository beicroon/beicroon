package com.beicroon.module.map.app.response.tengxun;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.response.BasePoiResponse;
import com.beicroon.module.map.entity.vo.PoiVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TengxunPoiResponse extends BasePoiResponse {

    @Schema(name = "status", description = "状态码，0为正常，其它为异常")
    private Integer status;

    @Schema(name = "message", description = "状态说明")
    private String message;

    @Schema(name = "data", description = "提示词数组，每项为一个POI对象")
    private List<Poi> data;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        res.setMapType(MapTypeEnums.TENGXUN.getCode());

        return res;
    }

    @Data
    public static class Poi implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonAlias(value = "title")
        @Schema(name = "name", description = "提示文字")
        private String name;

        @Schema(name = "address", description = "地址")
        private String address;

        @Schema(name = "location", description = "提示所述位置坐标")
        private Coordinate location;

        @Schema(name = "province", description = "省")
        private String province;

        @Schema(name = "city", description = "市")
        private String city;

        @Schema(name = "district", description = "区/县")
        private String district;

    }

}
