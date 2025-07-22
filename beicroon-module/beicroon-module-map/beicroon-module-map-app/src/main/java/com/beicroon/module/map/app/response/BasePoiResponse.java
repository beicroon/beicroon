package com.beicroon.module.map.app.response;

import com.beicroon.module.map.entity.vo.PoiVO;

import java.io.Serial;
import java.io.Serializable;

public abstract class BasePoiResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    abstract public PoiVO getPoi();

}
