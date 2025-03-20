package com.beicroon.service.rbac.dao.mapper;

import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.service.rbac.dao.model.RbacRoleModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RbacRoleMapper extends GenericMapper<RbacRoleModel> {

}
