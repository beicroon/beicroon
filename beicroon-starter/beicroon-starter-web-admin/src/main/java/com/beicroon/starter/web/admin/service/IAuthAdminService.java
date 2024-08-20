package com.beicroon.starter.web.admin.service;

import com.beicroon.starter.web.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminLoginVO;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;

import java.util.List;

public interface IAuthAdminService {

    AuthAdminLoginVO login(AuthAdminLoginDTO dto);

    List<AuthAdminMenuVO> menuTree();

}
