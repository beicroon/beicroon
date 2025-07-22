package com.beicroon.module.map.app.response.gaode;

import com.beicroon.module.map.app.response.BaseDirectionResponse;
import com.beicroon.module.map.entity.vo.DirectionVO;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodeDirectionDrivingResponse extends BaseDirectionResponse {

    @Schema(name = "status", description = "本次 API 访问状态，1 表示成功，0 表示失败")
    private String status;

    @Schema(name = "info", description = "访问状态说明，成功返回 'OK'")
    private String info;

    @Schema(name = "infocode", description = "返回状态编码，10000 表示正确")
    private String infocode;

    @Schema(name = "count", description = "方案总数")
    private String count;

    @Schema(name = "route", description = "路径方案")
    private Route route;

    @Override
    public DirectionVO getDirection() {
        if (this.route == null || this.route.getPaths() == null || this.route.getPaths().isEmpty()) {
            return null;
        }

        Path firstPath = route.paths.get(0);

        DirectionVO directionVO = new DirectionVO();

        directionVO.setDistance(firstPath.getDistance());
        directionVO.setDuration(firstPath.getDuration());

        return directionVO;
    }

    @Data
    public static class Route {

        @Schema(name = "origin", description = "起点经纬度")
        private String origin;

        @Schema(name = "destination", description = "终点经纬度")
        private String destination;

        @Schema(name = "paths", description = "方案列表")
        private List<Path> paths;

    }

    @Data
    public static class Path {

        @Schema(name = "distance", description = "路线距离（米）")
        private Integer distance;

        @Schema(name = "duration", description = "路线总耗时（秒）")
        private Integer duration;

        @Schema(name = "steps", description = "导航步骤列表")
        private List<Step> steps;

        @Schema(name = "cost", description = "费用信息（需 show_fields 启用）")
        private Cost cost;

    }

    @Data
    public static class Step {

        @Schema(name = "instruction", description = "骑行/驾车指示")
        private String instruction;

        @Schema(name = "orientation", description = "进入该道路的方向")
        private String orientation;

        @JsonAlias("road_name")
        @Schema(name = "roadName", description = "当前路段名称")
        private String roadName;

        @JsonAlias("step_distance")
        @Schema(name = "stepDistance", description = "当前路段长度（米）")
        private Integer stepDistance;

        @Schema(name = "duration", description = "当前路段耗时（秒）")
        private Integer duration;

    }

    @Data
    public static class Cost {

        @Schema(name = "duration", description = "方案总耗时（秒）")
        private Integer duration;

    }

}
