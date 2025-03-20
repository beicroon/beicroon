package com.beicroon.service.rbac.dao.mapper;

import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.service.rbac.dao.model.RbacAccountModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RbacAccountMapper extends GenericMapper<RbacAccountModel> {

}
