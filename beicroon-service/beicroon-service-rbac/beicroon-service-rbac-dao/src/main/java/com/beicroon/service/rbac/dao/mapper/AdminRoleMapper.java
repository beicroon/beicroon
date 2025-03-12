package com.beicroon.service.rbac.dao.mapper;

import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.service.rbac.dao.model.AdminRoleModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRoleMapper extends GenericMapper<AdminRoleModel> {

}
