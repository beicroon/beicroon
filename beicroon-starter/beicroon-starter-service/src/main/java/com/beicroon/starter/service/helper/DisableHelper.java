package com.beicroon.starter.service.helper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.starter.mysql.model.DisableModel;

import java.time.LocalDateTime;
import java.util.Collection;

public final class DisableHelper {

    private DisableHelper() {

    }

    public static <T extends DisableModel> boolean hasBeenDisabled(T t) {
        return t.getDisabledAt() != null;
    }

    public static <T extends DisableModel> void disable(T t) {
        AuthUser authUser = AuthUtils.getUser();

        t.setDisabledAt(LocalDateTime.now());
        t.setDisabledId(authUser.getId());
        t.setDisabledCode(authUser.getCode());
        t.setDisabledName(authUser.getNickname());
    }

    public static <T extends DisableModel> UpdateWrapper<T> disable(Long id) {
        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.eq("`id`", id);

        return DisableHelper.disable(wrapper);
    }

    public static <T extends DisableModel> UpdateWrapper<T> disable(Collection<Long> ids) {
        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.in("`id`", ids);

        return DisableHelper.disable(wrapper);
    }

    public static <T extends DisableModel> UpdateWrapper<T> disable(UpdateWrapper<T> wrapper) {
        AuthUser authUser = AuthUtils.getUser();

        wrapper.set(SystemConstant.DISABLE_KEY_NAME, LocalDateTime.now());
        wrapper.set("`disabled_id`", authUser.getId());
        wrapper.set("`disabled_code`", authUser.getCode());
        wrapper.set("`disabled_name`", authUser.getNickname());

        return wrapper;
    }

    public static <T extends DisableModel> UpdateWrapper<T> enable(Long id) {
        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.eq("`id`", id);

        return DisableHelper.enable(wrapper);
    }

    public static <T extends DisableModel> UpdateWrapper<T> enable(Collection<Long> ids) {
        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.in("`id`", ids);

        return DisableHelper.enable(wrapper);
    }

    public static <T extends DisableModel> UpdateWrapper<T> enable(UpdateWrapper<T> wrapper) {
        wrapper.set(SystemConstant.DISABLE_KEY_NAME, null);
        wrapper.set("`disabled_id`", 0L);
        wrapper.set("`disabled_code`", "");
        wrapper.set("`disabled_name`", "");

        return wrapper;
    }

}
