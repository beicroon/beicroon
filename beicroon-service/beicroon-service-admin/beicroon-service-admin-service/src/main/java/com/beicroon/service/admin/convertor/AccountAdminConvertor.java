package com.beicroon.service.admin.convertor;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.service.admin.model.AccountModel;
import com.beicroon.starter.service.convertor.GenericConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminConvertor extends GenericConvertor<AccountModel, AccountAdminCreateDTO, AccountAdminUpdateDTO, AccountAdminBaseVO, AccountAdminDetailVO, AccountAdminPageVO> {

}
