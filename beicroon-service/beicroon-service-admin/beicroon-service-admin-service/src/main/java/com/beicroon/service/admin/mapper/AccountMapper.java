package com.beicroon.service.admin.mapper;

import com.beicroon.service.admin.model.AccountModel;
import com.beicroon.starter.mysql.mapper.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends GenericMapper<AccountModel> {

}
