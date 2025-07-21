package com.beicroon.starter.dao.helper;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.database.model.DisableModel;
import com.beicroon.construct.utils.TimeUtils;

public final class DisableHelper {

    private DisableHelper() {

    }

    public static <T extends DisableModel> Boolean isDisabled(T t) {
        return t.getDisabledAt() != null;
    }

    public static <T extends DisableModel> void disable(T t) {
        AuthUser authUser = AuthUtils.getUser();

        t.setDisabledAt(TimeUtils.now());
        t.setDisabledId(authUser.getId());
        t.setDisabledCode(authUser.getCode());
        t.setDisabledName(authUser.getName());
    }

}
