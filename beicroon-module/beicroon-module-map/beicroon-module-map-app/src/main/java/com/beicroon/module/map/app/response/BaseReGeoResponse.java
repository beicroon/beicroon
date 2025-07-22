package com.beicroon.module.map.app.response;

import com.beicroon.construct.base.entity.Coordinate;
import com.beicroon.module.map.entity.vo.ReGeoVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseReGeoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public ReGeoVO getReGeo(Coordinate coordinate);

}
