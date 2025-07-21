package com.beicroon.module.file.app.utils;

import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.module.file.dao.bo.FileInfoBO;
import com.beicroon.module.file.enums.FileTypeEnums;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public final class MultipartFileUtils {

    private MultipartFileUtils() {

    }

    public static byte[] readFileBytes(MultipartFile file) {
        try {
            InputStream stream = file.getInputStream();

            return stream.readAllBytes();
        } catch (IOException ex) {
            throw ExceptionUtils.business("文件读取失败");
        }
    }

    public static FileInfoBO getFileInfo(MultipartFile file) {
        FileInfoBO fileInfo = new FileInfoBO();

        fileInfo.setFileType(MultipartFileUtils.getFileType(file).getCode());

        String filename = file.getOriginalFilename();

        if (EmptyUtils.isNotEmpty(filename)) {
            int index = filename.lastIndexOf(".");

            if (index == -1) {
                fileInfo.setFileName(filename);
            } else {
                fileInfo.setFileName(filename.substring(0, index));
                fileInfo.setFileExt(filename.substring(index + 1));
            }
        }

        fileInfo.setFileSize(file.getSize());

        return fileInfo;
    }

    public static FileTypeEnums getFileType(MultipartFile file) {
        String mimeType = file.getContentType();

        if (EmptyUtils.isNotEmpty(mimeType)) {
            if (mimeType.startsWith(FileTypeEnums.IMAGE.getPrefix())) {
                return FileTypeEnums.IMAGE;
            }
        }

        return FileTypeEnums.UNKNOW;
    }

}
