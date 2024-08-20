package com.beicroon.service.admin.web.service;

import com.beicroon.service.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminMenuVO;

import java.util.List;

public interface IAuthAdminService {

    AuthAdminLoginVO login(AuthAdminLoginDTO dto);

    List<AuthAdminMenuVO> menuTree();

}
