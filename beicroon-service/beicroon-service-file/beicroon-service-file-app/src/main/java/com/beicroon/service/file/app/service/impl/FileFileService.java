package com.beicroon.service.file.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.file.app.service.IFileFileService;
import com.beicroon.service.file.dao.convertor.FileFileConvertor;
import com.beicroon.service.file.dao.manager.FileFileManager;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.dao.repository.FileFileRepository;
import com.beicroon.service.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.service.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.service.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.service.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.service.file.entity.filefile.vo.FileFilePageVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFileService implements IFileFileService {

    @Resource
    private FileFileManager fileFileManager;

    @Resource
    private FileFileConvertor fileFileConvertor;

    @Resource
    private FileFileRepository fileFileRepository;

    @Override
    public List<TabVO> tab(FileFileQueryDTO dto) {
        return this.fileFileRepository.tab(dto);
    }

    @Override
    public List<FileFileBaseVO> list(FileFileQueryDTO dto) {
        List<FileFileModel> list = this.fileFileRepository.list(dto);

        return this.fileFileConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<FileFilePageVO> page(FileFileQueryDTO dto) {
        PageInfo<FileFileModel> page = this.fileFileRepository.page(dto);

        return this.fileFileConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(FileFileCreateDTO dto) {
        FileFileModel model = this.fileFileConvertor.toCreator(dto);

        return this.fileFileRepository.insert(model);
    }

    @Override
    public Boolean update(FileFileUpdateDTO dto) {
        FileFileModel model = this.fileFileConvertor.toUpdater(dto);

        return this.fileFileRepository.updateById(model);
    }

    @Override
    public FileFileBaseVO show(Long id) {
        FileFileModel model = this.fileFileRepository.getById(id);

        return this.fileFileConvertor.toBaseVO(model);
    }

    @Override
    public FileFileDetailVO detail(Long id) {
        FileFileModel model = this.fileFileRepository.getById(id);

        return this.fileFileConvertor.toDetailVO(model);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.fileFileRepository.removeByIds(dto.getIds());
    }

}
