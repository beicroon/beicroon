package com.beicroon.service.address.web.response.tengxun;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.response.BasePoiResponse;
import com.beicroon.service.address.web.vo.PoiVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TengxunPoiResponse extends BasePoiResponse {

    @ApiModelProperty(name = "状态码，0为正常，其它为异常")
    private Integer status;

    @ApiModelProperty(name = "状态说明")
    private String message;

    @ApiModelProperty(name = "提示词数组，每项为一个POI对象")
    private List<Poi> data;

    @Override
    public PoiVO getPoi() {
        PoiVO res = new PoiVO();

        return res;
    }

    @Data
    public static class Poi implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonAlias(value = "title")
        @ApiModelProperty(name = "提示文字")
        private String name;

        @ApiModelProperty(name = "地址")
        private String address;

        @ApiModelProperty(name = "提示所述位置坐标")
        private Coordinate location;

        @ApiModelProperty(name = "省")
        private String province;

        @ApiModelProperty(name = "市")
        private String city;

        @ApiModelProperty(name = "区/县")
        private String district;

    }

}
