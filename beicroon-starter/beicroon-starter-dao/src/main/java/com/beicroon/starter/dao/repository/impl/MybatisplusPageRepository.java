package com.beicroon.starter.dao.repository.impl;

import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.starter.dao.repository.GenericPageRepository;

public abstract class MybatisplusPageRepository<Q extends QueryDTO, M extends GenericMapper<T>, T extends GenericModel> extends MybatisplusRepository<M, T> implements GenericPageRepository<Q, T> {

}
