package com.beicroon.service.rbac.app.service;

import com.beicroon.service.rbac.entity.rbacauth.dto.RbacAuthLoginDTO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthLoginVO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthResourceVO;

import java.util.List;

public interface IRbacAuthService {

    RbacAuthLoginVO login(RbacAuthLoginDTO dto);

    List<RbacAuthResourceVO> resource();

    Boolean logout();

}
