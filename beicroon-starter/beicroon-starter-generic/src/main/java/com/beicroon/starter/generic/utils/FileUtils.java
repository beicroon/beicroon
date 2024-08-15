package com.beicroon.starter.generic.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

public class FileUtils {

    public static final String PACKAGE_SERVICE = "service";

    public static final String PACKAGE_DAO = "dao";

    public static final String PACKAGE_DAO_CONVERTOR = "convertor";

    public static final String PACKAGE_DAO_HELPER = "helper";

    public static final String PACKAGE_DAO_MANAGER = "manager";

    public static final String PACKAGE_DAO_MAPPER = "mapper";

    public static final String PACKAGE_DAO_MODEL = "model";

    public static final String PACKAGE_DAO_REPOSITORY = "repository";

    public static final String PACKAGE_ENTITY = "entity";

    public static final String PACKAGE_GENERIC = "generic";

    public static final String PACKAGE_WEB = "web";

    public static final String PACKAGE_WEB_CONSUMER = "consumer";

    public static final String PACKAGE_WEB_CONTROLLER = "controller";

    public static final String PACKAGE_WEB_CONTROLLER_HOME = FileUtils.joinPaths("controller", "home");

    public static final String PACKAGE_WEB_CONTROLLER_ADMIN = FileUtils.joinPaths("controller", "admin");

    public static final String PACKAGE_WEB_PRODUCER = "producer";

    public static final String PACKAGE_WEB_RPC = "rpc";

    public static final String PACKAGE_WEB_RPC_IMPL = FileUtils.joinPaths("rpc", "impl");

    public static final String PACKAGE_WEB_SERVICE = "service";

    public static final String PACKAGE_WEB_SERVICE_IMPL = FileUtils.joinPaths("service", "impl");

    public static final String PACKAGE_WEB_TASK = "task";

    public static final String CLASS_GENERATOR = "Generator";

    public static final String DIR_JAVA = FileUtils.joinPaths("src", "main", "java");

    public static final String DIR_RESOURCES = FileUtils.joinPaths("src", "main", "resources");

    private FileUtils() {

    }

    public static File getDaoPath(File rootPath, String serviceName) {
        File path = new File(rootPath, String.format("%s-%s", serviceName, PACKAGE_DAO));

        return FileUtils.mkdir(path);
    }

    public static File getEntityPath(File rootPath, String serviceName) {
        File path = new File(rootPath, String.format("%s-%s", serviceName, PACKAGE_ENTITY));

        return FileUtils.mkdir(path);
    }

    public static File getGenericPath(File rootPath, String serviceName) {
        File path = new File(rootPath, String.format("%s-%s", serviceName, PACKAGE_GENERIC));

        return FileUtils.mkdir(path);
    }

    public static File getWebPath(File rootPath, String serviceName) {
        File path = new File(rootPath, String.format("%s-%s", serviceName, PACKAGE_WEB));

        return FileUtils.mkdir(path);
    }

    public static File getConvertorPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_CONVERTOR;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getHelperPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_HELPER;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getManagerPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MANAGER;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getMapperPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MAPPER;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getModelPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MODEL;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getRepositoryPath(File daoPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_REPOSITORY;

        return FileUtils.mkdir(new File(daoPath, src));
    }

    public static File getConsumerPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONSUMER;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getControllerPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getControllerHomePath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER_HOME;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getControllerAdminPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER_ADMIN;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getProducerPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_PRODUCER;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getRpcPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_RPC;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getRpcImplPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_RPC_IMPL;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getServicePath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_SERVICE;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getServiceImplPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_SERVICE_IMPL;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getTaskPath(File webPath, List<String> basePackages) {
        String src = DIR_JAVA + FileUtils.getSeparator()
                + FileUtils.joinPaths(basePackages) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_TASK;

        return FileUtils.mkdir(new File(webPath, src));
    }

    public static File getJavaFile(File rootPath, String className) {
        return new File(rootPath, className + ".java");
    }

    public static void writeFileIfNotExists(File target, String content) {
        if (target.isFile()) {
            System.out.printf("跳过已存在文件:%s", target);
            System.out.println();

            return;
        }

        System.out.printf("写入内容:%s", target);
        System.out.println();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));

            writer.write(content);

            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeFile(String target, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));

            writer.write(content);

            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static File mkdir(File file) {
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException(String.format("文件创建失败:{%s}", file.getAbsoluteFile()));
        }

        return file;
    }

    public static String getSeparator() {
        return Matcher.quoteReplacement(File.separator);
    }

    public static String joinPaths(String... paths) {
        return String.join(FileUtils.getSeparator(), paths);
    }

    public static String joinPaths(List<String> paths) {
        return String.join(FileUtils.getSeparator(), paths);
    }

}
