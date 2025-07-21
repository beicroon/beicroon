package com.beicroon.module.file.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.module.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.module.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.module.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.module.file.entity.filefile.vo.FileFilePageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileFileService {

    List<TabVO> tab(FileFileQueryDTO dto);

    List<FileFileBaseVO> list(FileFileQueryDTO dto);

    PageInfo<FileFilePageVO> page(FileFileQueryDTO dto);

    Boolean create(FileFileCreateDTO dto);

    Boolean update(FileFileUpdateDTO dto);

    FileFileBaseVO show(Long id);

    FileFileDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

    FileFileBaseVO uploadFile(MultipartFile file);

    FileFileBaseVO uploadImage(MultipartFile image);

}
