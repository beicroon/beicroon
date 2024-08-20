package com.beicroon.starter.web.admin.convertor;

import com.beicroon.starter.dao.convertor.GenericConvertor;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminConvertor extends GenericConvertor<AccountAdminModel, AccountAdminCreateDTO, AccountAdminUpdateDTO, AccountAdminBaseVO, AccountAdminDetailVO, AccountAdminPageVO> {

}
