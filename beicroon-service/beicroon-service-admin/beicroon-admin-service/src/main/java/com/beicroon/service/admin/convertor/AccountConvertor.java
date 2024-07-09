package com.beicroon.service.admin.convertor;

import com.beicroon.service.admin.entity.account.dto.AccountCreateDTO;
import com.beicroon.service.admin.entity.account.dto.AccountUpdateDTO;
import com.beicroon.service.admin.entity.account.vo.AccountBaseVO;
import com.beicroon.service.admin.entity.account.vo.AccountDetailVO;
import com.beicroon.service.admin.entity.account.vo.AccountPageVO;
import com.beicroon.service.admin.model.AccountModel;
import com.beicroon.starter.service.convertor.GenericConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountConvertor extends GenericConvertor<AccountModel, AccountCreateDTO, AccountUpdateDTO, AccountBaseVO, AccountDetailVO, AccountPageVO> {

}
