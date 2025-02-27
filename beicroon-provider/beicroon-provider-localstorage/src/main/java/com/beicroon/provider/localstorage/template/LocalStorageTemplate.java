package com.beicroon.provider.localstorage.template;

import com.beicroon.construct.enums.DateTimeEnums;
import com.beicroon.construct.oss.template.IOssTemplate;
import com.beicroon.construct.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

@Component
public class LocalStorageTemplate implements IOssTemplate {

    @Value("${beicroon.oss.localstorage.static-path:static}")
    private String staticPath;

    @Override
    public String getName() {
        return "localstorage";
    }

    @Override
    public String upload(byte[] bytes, String filename, String ext) {
        String datePath = DateTimeEnums.Y_M_D.getFormatter().format(LocalDate.now());

        String storagePath = FileUtils.getPath(ext, datePath);

        File directory = new File(staticPath, storagePath);

        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("无法创建目录: " + directory.getAbsolutePath());
        }

        String fullFilename = filename + "." + ext;

        String filepath = FileUtils.getPath(storagePath, fullFilename);

        FileUtils.writeContent(new File(staticPath, filepath), bytes);

        return filepath;
    }

}
