package com.beicroon.provider.mybatisplus.repository;

import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.provider.mybatisplus.mapper.GenericMapper;

public abstract class MybatisplusPageRepository<Q extends QueryDTO, M extends GenericMapper<T>, T extends GenericModel> extends MybatisplusRepository<M, T> implements GenericPageRepository<Q, T> {

}
