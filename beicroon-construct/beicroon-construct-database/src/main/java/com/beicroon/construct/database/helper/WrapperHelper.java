package com.beicroon.construct.database.helper;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.wrapper.IUpdateWrapper;

import java.time.LocalDateTime;

public final class WrapperHelper {

    private WrapperHelper() {
    }

    public static <T extends GenericModel> void fillWrapperRemover(IUpdateWrapper<T> wrapper) {
        AuthUser user = AuthUtils.getUser();

        wrapper.set("`removed_at`", LocalDateTime.now());
        wrapper.set("`remover_id`", user.getId());
        wrapper.set("`remover_code`", user.getCode());
        wrapper.set("`remover_name`", user.getName());
    }

}
