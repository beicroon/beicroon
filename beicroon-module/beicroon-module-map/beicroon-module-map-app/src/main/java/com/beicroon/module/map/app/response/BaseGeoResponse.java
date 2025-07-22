package com.beicroon.module.map.app.response;

import com.beicroon.module.map.entity.vo.GeoVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseGeoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public GeoVO getGeo();

}
