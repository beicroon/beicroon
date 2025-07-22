package com.beicroon.module.map.app.response.gaode;

import com.beicroon.module.map.app.response.BaseDirectionResponse;
import com.beicroon.module.map.entity.vo.DirectionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GaodeDirectionBicyclingResponse extends BaseDirectionResponse {

    @Override
    public DirectionVO getDirection() {
        return null;
    }

}
