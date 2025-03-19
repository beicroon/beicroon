package com.beicroon.service.file.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.file.app.service.IAdminFileService;
import com.beicroon.service.file.dao.convertor.AdminFileConvertor;
import com.beicroon.service.file.dao.manager.AdminFileManager;
import com.beicroon.service.file.dao.model.AdminFileModel;
import com.beicroon.service.file.dao.repository.AdminFileRepository;
import com.beicroon.service.file.entity.adminfile.vo.AdminFileBaseVO;
import com.beicroon.starter.oss.manager.OssManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class AdminFileService implements IAdminFileService {

    @Resource
    private AdminFileManager adminFileManager;

    @Resource
    private AdminFileConvertor adminFileConvertor;

    @Resource
    private AdminFileRepository adminFileRepository;

    @Resource
    private OssManager ossManager;

    @Override
    public AdminFileBaseVO upload(MultipartFile file) {
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

        AdminFileModel model = this.adminFileManager.genericModel(file);

        model.setUrl(this.ossManager.upload(bytes, model.getName(), model.getExt()));

        this.adminFileRepository.insert(model);

        return this.adminFileConvertor.toBaseVO(model);
    }

    @Override
    public AdminFileBaseVO uploadImage(MultipartFile image) {
        String contentType = image.getContentType();

        if (contentType == null || EmptyUtils.isEmpty(contentType) || !contentType.startsWith("image")) {
            throw ExceptionUtils.business("请选择图片上传");
        }

        return this.upload(image);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.adminFileRepository.removeByIds(dto.getIds());
    }

}
