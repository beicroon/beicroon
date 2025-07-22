package com.beicroon.module.map.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum DirectionStrategyEnums {
    DEFAULT("default", "默认", "32", null, null),
    SPEED_FIRST("speed_first", "速度优先", "0", null, null),
    COST_FIRST("cost_first", "费用优先", "1", null, null),
    FASTEST_NORMAL("fastest_normal", "常规最快", "2", null, null),
    AVOID_CONGESTION("avoid_congestion", "躲避拥堵", "33", null, null),
    HIGHWAY_FIRST("highway_first", "高速优先", "34", null, null),
    NO_HIGHWAY("no_highway", "不走高速", "35", null, null),
    LESS_TOLL("less_toll", "少收费", "36", null, null),
    MAIN_ROAD_FIRST("main_road_first", "大路优先", "37", null, null),
    FASTEST("fastest", "速度最快", "38", null, null),
    AVOID_CONGESTION_HIGHWAY_FIRST("avoid_congestion_highway_first", "躲避拥堵＋高速优先", "39", null, null),
    AVOID_CONGESTION_NO_HIGHWAY("avoid_congestion_no_highway", "躲避拥堵＋不走高速", "40", null, null),
    AVOID_CONGESTION_LESS_TOLL("avoid_congestion_less_toll", "躲避拥堵＋少收费", "41", null, null),
    LESS_TOLL_NO_HIGHWAY("less_toll_no_highway", "少收费＋不走高速", "42", null, null),
    AVOID_CONGESTION_LESS_TOLL_NO_HIGHWAY("avoid_congestion_less_toll_no_highway", "躲避拥堵＋少收费＋不走高速", "43", null, null),
    AVOID_CONGESTION_MAIN_ROAD_FIRST("avoid_congestion_main_road_first", "躲避拥堵＋大路优先", "44", null, null),
    AVOID_CONGESTION_FASTEST("avoid_congestion_fastest", "躲避拥堵＋速度最快", "45", null, null)
    ;

    private static final Map<String, DirectionStrategyEnums> MAP = new HashMap<>();

    static {
        for (DirectionStrategyEnums value : DirectionStrategyEnums.values()) {
            MAP.put(value.getCode(), value);
        }
    }

    private final String code;

    private final String name;

    private final String gaode;

    private final String baidu;

    private final String tengxun;

    public static DirectionStrategyEnums codeOf(String code) {
        return MAP.get(code);
    }

}
