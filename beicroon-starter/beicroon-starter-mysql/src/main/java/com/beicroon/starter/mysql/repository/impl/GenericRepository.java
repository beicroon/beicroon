package com.beicroon.starter.mysql.repository.impl;

import com.beicroon.construct.entity.QueryDTO;
import com.beicroon.starter.mysql.mapper.GenericMapper;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.mysql.repository.IGenericRepository;

public abstract class GenericRepository<Q extends QueryDTO, M extends GenericMapper<T>, T extends GenericModel> extends SuperRepository<M, T> implements IGenericRepository<Q, T> {

}
