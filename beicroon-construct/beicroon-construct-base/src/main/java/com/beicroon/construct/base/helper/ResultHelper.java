package com.beicroon.construct.base.helper;

import com.beicroon.construct.base.entity.Result;

public final class ResultHelper {

    private ResultHelper() {

    }

    public boolean hasError(Result<?> result) {
        return result.getCode() != null && result.getCode() > 0;
    }

}
