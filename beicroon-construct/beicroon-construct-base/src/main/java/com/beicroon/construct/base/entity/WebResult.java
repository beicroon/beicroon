package com.beicroon.construct.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WebResult<T> extends Result<T> {

    public static <T> WebResult<T> success() {
        WebResult<T> result = new WebResult<>();

        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(null);

        return result;
    }

    public static <T> WebResult<T> success(T data) {
        WebResult<T> result = new WebResult<>();

        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(data);

        return result;
    }

    public static <T> WebResult<List<T>> success(com.beicroon.construct.base.entity.PageInfo<T> data) {
        WebResult<List<T>> result = new WebResult<>();

        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(data.getData());
        result.setPageInfo(new PageInfo(data.getPage(), data.getSize(), data.getTotal()));

        return result;
    }

    public static <T> WebResult<T> error(Integer code, String message) {
        WebResult<T> result = new WebResult<>();

        result.setCode(code);
        result.setMessage(message);

        return result;
    }

}
