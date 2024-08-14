package com.beicroon.starter.generic.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class FileUtils {

    private static final String PACKAGE_SERVICE = "service";

    private static final String PACKAGE_DAO = "dao";

    private static final String PACKAGE_DAO_CONVERTOR = "convertor";

    private static final String PACKAGE_DAO_HELPER = "helper";

    private static final String PACKAGE_DAO_MANAGER = "manager";

    private static final String PACKAGE_DAO_MAPPER = "mapper";

    private static final String PACKAGE_DAO_MODEL = "model";

    private static final String PACKAGE_DAO_REPOSITORY = "repository";

    private static final String PACKAGE_ENTITY = "entity";

    private static final String PACKAGE_WEB = "web";

    private static final String PACKAGE_WEB_CONSUMER = "consumer";

    private static final String PACKAGE_WEB_CONTROLLER = "controller";

    private static final String PACKAGE_WEB_CONTROLLER_HOME = FileUtils.joinPaths("controller", "home");

    private static final String PACKAGE_WEB_CONTROLLER_ADMIN = FileUtils.joinPaths("controller", "admin");

    private static final String PACKAGE_WEB_PRODUCER = "producer";

    private static final String PACKAGE_WEB_RPC = "rpc";

    private static final String PACKAGE_WEB_RPC_IMPL = FileUtils.joinPaths("rpc", "impl");

    private static final String PACKAGE_WEB_SERVICE = "service";

    private static final String PACKAGE_WEB_SERVICE_IMPL = FileUtils.joinPaths("service", "impl");

    private static final String PACKAGE_WEB_TASK = "task";

    public static final String DIR_JAVA = FileUtils.joinPaths("src", "main", "java");

    public static final String DIR_RESOURCES = FileUtils.joinPaths("src", "main", "resources");

    private FileUtils() {

    }

    public static String getConvertorPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_CONVERTOR + FileUtils.getSeparator();
    }

    public static String getHelperPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_HELPER + FileUtils.getSeparator();
    }

    public static String getManagerPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MANAGER + FileUtils.getSeparator();
    }

    public static String getMapperPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MAPPER + FileUtils.getSeparator();
    }

    public static String getModelPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_MODEL + FileUtils.getSeparator();
    }

    public static String getRepositoryPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_DAO + FileUtils.getSeparator()
                + PACKAGE_DAO_REPOSITORY + FileUtils.getSeparator();
    }

    public static String getEntityPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_ENTITY + FileUtils.getSeparator();
    }


    public static String getWebPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator();
    }

    public static String getConsumerPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONSUMER + FileUtils.getSeparator();
    }

    public static String getControllerPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER + FileUtils.getSeparator();
    }

    public static String getControllerHomePath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER_HOME + FileUtils.getSeparator();
    }

    public static String getControllerAdminPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_CONTROLLER_ADMIN + FileUtils.getSeparator();
    }

    public static String getProducerPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_PRODUCER + FileUtils.getSeparator();
    }

    public static String getRpcPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_RPC + FileUtils.getSeparator();
    }

    public static String getRpcImplPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_RPC_IMPL + FileUtils.getSeparator();
    }

    public static String getServicePath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_SERVICE + FileUtils.getSeparator();
    }

    public static String getServiceImplPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_SERVICE_IMPL + FileUtils.getSeparator();
    }

    public static String getTaskPath(String moduleName) {
        return DIR_JAVA + FileUtils.getSeparator()
                + PACKAGE_SERVICE + FileUtils.getSeparator()
                + FileUtils.splitAndJoinPaths(moduleName) + FileUtils.getSeparator()
                + PACKAGE_WEB + FileUtils.getSeparator()
                + PACKAGE_WEB_TASK + FileUtils.getSeparator();
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

    public static void mkdir(File file) {
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException(String.format("文件创建失败:{%s}", file.getAbsoluteFile()));
        }
    }

    public static String getSeparator() {
        return Matcher.quoteReplacement(File.separator);
    }

    public static String splitAndJoinPaths(String moduleName) {
        String[] modules = moduleName.split("-");

        return String.join(FileUtils.getSeparator(), modules);
    }

    public static String joinPaths(String... paths) {
        return String.join(FileUtils.getSeparator(), paths);
    }

}
