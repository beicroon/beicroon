package com.beicroon.service.file.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import org.springframework.web.multipart.MultipartFile;

public interface IFileFileService {

    FileFileBaseVO upload(MultipartFile file);

    FileFileBaseVO uploadImage(MultipartFile image);

    Boolean remove(IdsDTO dto);

}
