package com.beicroon.module.map.app.response.baidu;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.app.response.BaseGeoResponse;
import com.beicroon.module.map.entity.vo.GeoVO;
import com.beicroon.module.map.enums.MapTypeEnums;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduGeoResponse extends BaseGeoResponse {

    @Schema(name = "status", description = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @Schema(name = "result", description = "返回的结果")
    private Result result;

    @Override
    public GeoVO getGeo() {
        if (this.result == null || this.result.getLocation() == null) {
            return null;
        }

        GeoVO res = new GeoVO();

        res.setMapType(MapTypeEnums.BAIDU.getCode());
        res.setCoordinate(new Coordinate(this.result.getLocation().getLng(), this.result.getLocation().getLat()));

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(name = "location", description = "经纬度坐标")
        private Coordinate location;

        @Schema(name = "precise", description = "位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。")
        private Integer precise;

        @Schema(name = "confidence", description = "描述打点绝对精度（即坐标点的误差范围）。")
        private Integer confidence;

        @Schema(name = "comprehension", description = "描述地址理解程度。分值范围0-100，分值越大，服务对地址理解程度越高（建议以该字段作为解析结果判断标准）")
        private Integer comprehension;

    }

}
