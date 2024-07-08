package com.beicroon.construct.entity;

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

    public static <T> WebResult<List<T>> success(PageInfo<T> data) {
        WebResult<List<T>> result = new WebResult<>();

        result.setCode(0);
        result.setMessage("操作成功");
        result.setData(data.getData());
        result.setPage(new Page(data.getPageNum(), data.getPageSize(), data.getTotalSize()));

        return result;
    }

    public static <T> WebResult<T> error(Integer code, String message) {
        WebResult<T> result = new WebResult<>();

        result.setCode(code);
        result.setMessage(message);

        return result;
    }

}
