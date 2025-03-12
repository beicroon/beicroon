package com.beicroon.service.rbac.app.service;

import com.beicroon.service.rbac.entity.auth.dto.AdminAuthLoginDTO;
import com.beicroon.service.rbac.entity.auth.vo.AdminAuthLoginVO;
import com.beicroon.service.rbac.entity.auth.vo.AdminAuthResourceVO;

import java.util.List;

public interface IAdminAuthService {

    AdminAuthLoginVO login(AdminAuthLoginDTO dto);

    List<AdminAuthResourceVO> resource();

    Boolean logout();

}
