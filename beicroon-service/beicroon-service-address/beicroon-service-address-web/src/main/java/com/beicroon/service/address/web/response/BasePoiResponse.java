package com.beicroon.service.address.web.response;

import com.beicroon.service.address.web.vo.PoiVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BasePoiResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public PoiVO getPoi();

}
