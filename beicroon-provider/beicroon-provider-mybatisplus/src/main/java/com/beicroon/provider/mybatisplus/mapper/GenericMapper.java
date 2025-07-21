package com.beicroon.provider.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beicroon.construct.database.model.GenericModel;

public interface GenericMapper<T extends GenericModel> extends BaseMapper<T> {
}
