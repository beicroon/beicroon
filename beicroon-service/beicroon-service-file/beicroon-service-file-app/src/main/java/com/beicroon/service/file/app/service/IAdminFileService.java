package com.beicroon.service.file.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.service.file.entity.adminfile.vo.AdminFileBaseVO;
import org.springframework.web.multipart.MultipartFile;

public interface IAdminFileService {

    AdminFileBaseVO upload(MultipartFile file);

    AdminFileBaseVO uploadImage(MultipartFile image);

    Boolean remove(IdsDTO dto);

}
