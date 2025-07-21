package com.beicroon.module.file.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.module.file.app.service.IFileFileService;
import com.beicroon.module.file.app.utils.MultipartFileUtils;
import com.beicroon.module.file.dao.bo.FileInfoBO;
import com.beicroon.module.file.dao.convertor.FileFileConvertor;
import com.beicroon.module.file.dao.manager.FileFileManager;
import com.beicroon.module.file.dao.model.FileFileModel;
import com.beicroon.module.file.dao.repository.FileFileRepository;
import com.beicroon.module.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileQueryDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.module.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.module.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.module.file.entity.filefile.vo.FileFilePageVO;
import com.beicroon.module.file.enums.FileTypeEnums;
import com.beicroon.starter.dao.manager.DBManager;
import com.beicroon.starter.oss.manager.OssManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileFileService implements IFileFileService {

    @Resource
    private DBManager dbManager;

    @Resource
    private OssManager ossManager;

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

        if (page.isEmpty()) {
            return new PageInfo<>(dto.getPageInfo());
        }

        return this.fileFileConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(FileFileCreateDTO dto) {
        FileFileModel creator = this.fileFileConvertor.toCreator(dto);

        return this.fileFileRepository.insert(creator);
    }

    @Override
    public Boolean update(FileFileUpdateDTO dto) {
        FileFileModel model = this.fileFileRepository.getByIdOrError(
                dto.getId(), "文件不存在或已删除"
        );

        FileFileModel updater = this.fileFileConvertor.toUpdater(model, dto);

        return this.fileFileRepository.updateById(updater);
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

    @Override
    public FileFileBaseVO uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw ExceptionUtils.business("文件不能为空");
        }

        byte[] bytes = MultipartFileUtils.readFileBytes(file);

        FileInfoBO fileInfo = MultipartFileUtils.getFileInfo(file);

        String url = this.ossManager.upload(bytes, fileInfo.getFileName(), fileInfo.getFileExt());

        FileFileModel creator = this.fileFileConvertor.toCreator(url, fileInfo);

        this.fileFileRepository.insert(creator);

        return this.fileFileConvertor.toBaseVO(creator);
    }

    @Override
    public FileFileBaseVO uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw ExceptionUtils.business("图片不能为空");
        }

        String contentType = image.getContentType();

        if (EmptyUtils.isEmpty(contentType) || !contentType.startsWith(FileTypeEnums.IMAGE.getPrefix())) {
            throw ExceptionUtils.business("请选择图片上传");
        }

        return this.uploadFile(image);
    }

}
