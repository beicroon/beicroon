package com.beicroon.service.address.web.response.baidu;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.response.BaseGeoResponse;
import com.beicroon.service.address.web.vo.GeoVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduGeoResponse extends BaseGeoResponse {

    @ApiModelProperty(name = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @ApiModelProperty(name = "返回的结果")
    private Result result;

    @Override
    public GeoVO getGeo() {
        if (this.result == null || this.result.getLocation() == null) {
            return null;
        }

        GeoVO res = new GeoVO();

        res.setCoordinate(new Coordinate(this.result.getLocation().getLng(), this.result.getLocation().getLat()));

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "经纬度坐标")
        private Coordinate location;

        @ApiModelProperty(name = "位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。")
        private Integer precise;

        @ApiModelProperty(name = "描述打点绝对精度（即坐标点的误差范围）。")
        private Integer confidence;

        @ApiModelProperty(name = "描述地址理解程度。分值范围0-100，分值越大，服务对地址理解程度越高（建议以该字段作为解析结果判断标准）")
        private Integer comprehension;

    }

}
