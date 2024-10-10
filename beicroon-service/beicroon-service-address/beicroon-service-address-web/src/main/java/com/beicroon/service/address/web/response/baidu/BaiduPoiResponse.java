package com.beicroon.service.address.web.response.baidu;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.response.BasePoiResponse;
import com.beicroon.service.address.web.vo.PoiVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiduPoiResponse extends BasePoiResponse {

    @ApiModelProperty(name = "本次API访问状态，如果成功返回0，如果失败返回其他数字。")
    private Integer status;

    @ApiModelProperty(name = "对API访问状态值的英文说明，如果成功返回ok，并返回结果字段，如果失败返回错误说明。")
    private String message;

    @ApiModelProperty(name = "返回的结果")
    private List<Result> result;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        return res;
    }

    @Data
    public static class Result implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @ApiModelProperty(name = "poi名称，单次请求最多返回10条结果")
        private String name;

        @ApiModelProperty(name = "poi经纬度坐标")
        private Coordinate location;

        @ApiModelProperty(name = "所属省份")
        private String province;

        @ApiModelProperty(name = "所属城市")
        private String city;

        @ApiModelProperty(name = "区县")
        private String district;

        @ApiModelProperty(name = "poi地址")
        private String address;

    }

}
