package com.beicroon.service.admin.web.service;

import com.beicroon.service.admin.entity.auth.admin.dto.AuthAdminLoginDTO;
import com.beicroon.service.admin.entity.auth.admin.vo.AuthAdminLoginVO;

public interface IAuthAdminService {

    AuthAdminLoginVO login(AuthAdminLoginDTO dto);

}
