package com.beicroon.service.file.dao.manager;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.service.file.dao.convertor.AdminFileConvertor;
import com.beicroon.service.file.dao.model.AdminFileModel;
import com.beicroon.service.file.dao.repository.AdminFileRepository;
import com.beicroon.starter.database.helper.ConvertorHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class AdminFileManager {

    @Resource
    private AdminFileConvertor adminFileConvertor;

    @Resource
    private AdminFileRepository adminFileRepository;

    public AdminFileModel genericModel(MultipartFile file) {
        String filename = file.getOriginalFilename();

        AdminFileModel model = new AdminFileModel();

        if (EmptyUtils.isNotEmpty(filename)) {
            int index = filename.lastIndexOf(".");

            if (index == -1) {
                model.setName(filename);
            } else {
                model.setName(filename.substring(0, index));
                model.setExt(filename.substring(index + 1));
            }
        }

        model.setSize(file.getSize());

        ConvertorHelper.fillCreator(model);

        return model;
    }

}
