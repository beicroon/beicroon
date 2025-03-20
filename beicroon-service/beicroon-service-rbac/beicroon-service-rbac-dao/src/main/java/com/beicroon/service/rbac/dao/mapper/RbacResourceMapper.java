package com.beicroon.service.rbac.dao.mapper;

import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RbacResourceMapper extends GenericMapper<RbacResourceModel> {

}
