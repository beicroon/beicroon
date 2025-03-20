package com.beicroon.service.file.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.file.app.service.IFileFileService;
import com.beicroon.service.file.dao.convertor.FileFileConvertor;
import com.beicroon.service.file.dao.manager.FileFileManager;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.dao.repository.FileFileRepository;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.starter.oss.manager.OssManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileFileService implements IFileFileService {

    @Resource
    private FileFileManager fileFileManager;

    @Resource
    private FileFileConvertor fileFileConvertor;

    @Resource
    private FileFileRepository fileFileRepository;

    @Resource
    private OssManager ossManager;

    @Override
    public FileFileBaseVO upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw ExceptionUtils.business("文件不能为空");
        }

        byte[] bytes;

        try {
            InputStream stream = file.getInputStream();

            bytes = stream.readAllBytes();
        } catch (IOException ex) {
            throw ExceptionUtils.business("文件读取失败");
        }

        FileFileModel model = this.fileFileConvertor.toCreator(file);

        model.setUrl(this.ossManager.upload(bytes, model.getName(), model.getExt()));

        this.fileFileRepository.insert(model);

        return this.fileFileConvertor.toBaseVO(model);
    }

    @Override
    public FileFileBaseVO uploadImage(MultipartFile image) {
        String contentType = image.getContentType();

        if (contentType == null || EmptyUtils.isEmpty(contentType) || !contentType.startsWith("image")) {
            throw ExceptionUtils.business("请选择图片上传");
        }

        return this.upload(image);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.fileFileRepository.removeByIds(dto.getIds());
    }

}
