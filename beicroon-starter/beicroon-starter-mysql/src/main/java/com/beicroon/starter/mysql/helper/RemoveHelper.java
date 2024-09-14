package com.beicroon.starter.mysql.helper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.starter.mysql.model.GenericModel;

import java.time.LocalDateTime;

public final class RemoveHelper {

    private RemoveHelper() {

    }

    public static <T extends GenericModel> void setRemoveWrapper(UpdateWrapper<T> wrapper) {
        AuthUser user = AuthUtils.getUser();

        wrapper.set("`removed_at`", LocalDateTime.now());
        wrapper.set("`remover_id`", user.getId());
        wrapper.set("`remover_code`", user.getCode());
        wrapper.set("`remover_name`", user.getNickname());
    }

}
