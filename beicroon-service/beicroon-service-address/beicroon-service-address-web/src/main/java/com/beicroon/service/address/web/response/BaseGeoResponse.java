package com.beicroon.service.address.web.response;

import com.beicroon.service.address.web.vo.GeoVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseGeoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public GeoVO getGeo();

}
