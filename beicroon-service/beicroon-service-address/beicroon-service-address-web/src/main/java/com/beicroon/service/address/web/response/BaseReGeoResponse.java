package com.beicroon.service.address.web.response;

import com.beicroon.construct.entity.Coordinate;
import com.beicroon.service.address.web.vo.ReGeoVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseReGeoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public ReGeoVO getReGeo(Coordinate coordinate);

}
