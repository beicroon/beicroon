package com.beicroon.service.admin.dao.mapper;

import com.beicroon.service.admin.dao.model.AccountModel;
import com.beicroon.starter.mysql.mapper.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends GenericMapper<AccountModel> {

}
