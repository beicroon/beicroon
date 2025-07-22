package com.beicroon.module.map.app.response;

import com.beicroon.module.map.entity.vo.DirectionVO;
import com.beicroon.module.map.entity.vo.GeoVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseDirectionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public DirectionVO getDirection();

}
