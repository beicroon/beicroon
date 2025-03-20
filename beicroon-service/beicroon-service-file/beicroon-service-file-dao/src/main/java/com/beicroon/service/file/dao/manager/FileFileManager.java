package com.beicroon.service.file.dao.manager;

import com.beicroon.service.file.dao.convertor.FileFileConvertor;
import com.beicroon.service.file.dao.repository.FileFileRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileFileManager {

    @Resource
    private FileFileConvertor fileFileConvertor;

    @Resource
    private FileFileRepository fileFileRepository;

}
