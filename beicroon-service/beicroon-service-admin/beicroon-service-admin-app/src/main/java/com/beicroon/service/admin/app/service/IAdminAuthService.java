package com.beicroon.service.admin.app.service;

import com.beicroon.service.admin.entity.auth.dto.AdminAuthLoginDTO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthLoginVO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthResourceVO;

import java.util.List;

public interface IAdminAuthService {

    AdminAuthLoginVO login(AdminAuthLoginDTO dto);

    List<AdminAuthResourceVO> resource();

    Boolean logout();

}
