package com.beicroon.starter.web.admin.mapper;

import com.beicroon.starter.mysql.mapper.GenericMapper;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountAdminMapper extends GenericMapper<AccountAdminModel> {

}
