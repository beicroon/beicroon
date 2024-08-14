package com.beicroon.service.admin.dao.convertor;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.service.admin.dao.model.AccountAdminModel;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.starter.dao.convertor.GenericConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminConvertor extends GenericConvertor<AccountAdminModel, AccountAdminCreateDTO, AccountAdminUpdateDTO, AccountAdminBaseVO, AccountAdminDetailVO, AccountAdminPageVO> {

}
