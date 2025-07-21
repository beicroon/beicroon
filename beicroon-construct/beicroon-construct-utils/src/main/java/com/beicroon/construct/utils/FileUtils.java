package com.beicroon.construct.utils;

import com.beicroon.construct.constant.SystemConstant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class FileUtils {

    public static String getExt(String filename) {
        if (EmptyUtils.isNotEmpty(filename)) {
            return null;
        }

        int index = filename.lastIndexOf(".");

        if (index == -1) {
            return null;
        }

        return filename.substring(index + 1).toLowerCase();
    }

    public static void writeIfNotExists(File path, String filename, String content) {
        File filepath = new File(path, filename);

        FileUtils.writeIfNotExists(filepath, content);
    }

    public static void writeIfNotExists(File file, String content) {
        if (file.isFile()) {
            return;
        }

        FileUtils.writeContent(file, content.getBytes(SystemConstant.CHARSET));
    }

    public static void writeContent(String filepath, byte[] content) {
        FileUtils.writeContent(new File(filepath), content);
    }

    public static void writeContent(File file, byte[] content) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        } catch (IOException e) {
            throw new RuntimeException("文件写入失败", e);
        }
    }

    public static void mkdir(File file) {
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException(String.format("文件创建失败:{%s}", file.getPath()));
        }
    }

    public static String getPath(String path, String... paths) {
        return Paths.get(path, paths).toString();
    }

}
