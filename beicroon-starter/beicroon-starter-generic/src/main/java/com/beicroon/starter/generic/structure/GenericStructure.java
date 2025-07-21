package com.beicroon.starter.generic.structure;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.FileUtils;
import com.beicroon.construct.utils.StringUtils;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class GenericStructure {

    private static final String PATH_APP = "app";

    private static final String PATH_APP_CONSUMER = "consumer";

    private static final String PATH_APP_CONTROLLER_ADMIN = FileUtils.getPath("controller", "admin");

    private static final String PATH_APP_CONTROLLER_HOME = FileUtils.getPath("controller", "home");

    private static final String PATH_APP_EVENT = "event";

    private static final String PATH_APP_LISTENER = "listener";

    private static final String PATH_APP_PRODUCER = "producer";

    private static final String PATH_APP_SERVICE = "service";

    private static final String PATH_APP_SERVICE_IMPL = FileUtils.getPath("service", "impl");

    private static final String PATH_APP_TASK = "task";

    private static final String PATH_DAO = "dao";

    private static final String PATH_CONSTANT = "constant";

    private static final String PATH_DAO_CONVERTOR = "convertor";

    private static final String PATH_DAO_HELPER = "helper";

    private static final String PATH_DAO_MANAGER = "manager";

    private static final String PATH_DAO_MAPPER = "mapper";

    private static final String PATH_DAO_MODEL = "model";

    private static final String PATH_DAO_REPOSITORY = "repository";

    private static final String PATH_DAO_TAB = "tab";

    private static final String PATH_DAO_TAB_IMPL = FileUtils.getPath("tab", "impl");

    private static final String PATH_ENTITY = "entity";

    private static final String PATH_ENUMS = "enums";

    private static final String PATH_FEIGN = "feign";

    private static final String PATH_FEIGN_API = "api";

    private static final String PATH_FEIGN_DTO = "dto";

    private static final String PATH_FEIGN_VO = "vo";

    private static final String PATH_INTERFACES = "interfaces";

    private static final String PATH_GENERIC = "generic";

    private static final String PATH_WEB = "web";

    private static final String PATH_MIGRATION = "migration";

    private static final String FILE_GIT_IGNORE = ".gitignore";

    private static final String FILE_POM = "pom.xml";

    private static final String FILE_MIGRATION = "V1.0.0-feature.sql";

    private static final String FILE_GENERATOR = "%sAppGenerator.java";

    private static final String FILE_APPLICATION = "%sWebApplication.java";

    private static final String FILE_YML = "application.yml";

    private static final String FILE_YML_DEV = "application-dev.yml";

    private static final String FILE_YML_FAT = "application-fat.yml";

    private static final String FILE_YML_UAT = "application-uat.yml";

    private static final String FILE_YML_PRO = "application-pro.yml";

    private static final String FILE_YML_LOCAL = "application-local.yml";

    private static final String FILE_TEST = "%sWebTests.java";

    private final File rootPath;

    private final String servicePrefix;

    private final String moduleName;

    private final String serviceName;

    private final String className;

    private final String basePackage;

    private final String commonPrefix;

    private final String commonPackage;

    private final String commonName;

    private final String serverPort;

    private final String databaseName;

    private final String modulePackage;

    private final String modulePath;

    private final String javaPath;

    private final String resourcesPath;

    private final String testPath;

    public GenericStructure(File rootPath, String servicePrefix, String commonPrefix, String moduleName, String basePackage) {
        this(rootPath, servicePrefix, commonPrefix, moduleName, basePackage, null, null);
    }

    public GenericStructure(File rootPath, String servicePrefix, String commonPrefix, String moduleName, String basePackage, String commonPackage, Integer serverPort) {
        this.rootPath = rootPath;

        this.servicePrefix = servicePrefix;

        this.moduleName = moduleName;

        this.serviceName = EmptyUtils.isEmpty(servicePrefix) ? moduleName : String.format("%s-%s", servicePrefix, moduleName);

        this.className = StringUtils.splitAndToFirstUpper(this.serviceName, "-");

        this.basePackage = basePackage;

        this.commonPrefix = commonPrefix;

        this.commonPackage = commonPackage == null ? "" : commonPackage;

        this.commonName = EmptyUtils.isEmpty(commonPrefix) ? moduleName : String.format("%s-%s", commonPrefix, moduleName);

        this.serverPort = serverPort == null ? "8080" : String.valueOf(serverPort);

        this.databaseName = this.serviceName.replace("-", "_");

        this.modulePackage = basePackage + "." + String.join(".", moduleName.split("-"));

        List<String> modulePackages = new ArrayList<>(Arrays.asList(this.modulePackage.split("\\.")));

        this.modulePath =  FileUtils.getPath(modulePackages.remove(0), modulePackages.toArray(String[]::new));

        this.javaPath = FileUtils.getPath("src", "main", "java");

        this.resourcesPath = FileUtils.getPath("src", "main", "resources");

        this.testPath = FileUtils.getPath("src", "test", "java");
    }

    public File getAppPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_APP));
    }

    public File getAppJavaPath() {
        return new File(this.getAppPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_APP));
    }

    public File getAppConsumerPath() {
        return new File(this.getAppJavaPath(), PATH_APP_CONSUMER);
    }

    public File getAppControllerAdminPath() {
        return new File(this.getAppJavaPath(), PATH_APP_CONTROLLER_ADMIN);
    }

    public File getAppControllerHomePath() {
        return new File(this.getAppJavaPath(), PATH_APP_CONTROLLER_HOME);
    }

    public File getAppEventPath() {
        return new File(this.getAppJavaPath(), PATH_APP_EVENT);
    }

    public File getAppListenerPath() {
        return new File(this.getAppJavaPath(), PATH_APP_LISTENER);
    }

    public File getAppProducerPath() {
        return new File(this.getAppJavaPath(), PATH_APP_PRODUCER);
    }

    public File getAppServicePath() {
        return new File(this.getAppJavaPath(), PATH_APP_SERVICE);
    }

    public File getAppServiceImplPath() {
        return new File(this.getAppJavaPath(), PATH_APP_SERVICE_IMPL);
    }

    public File getAppTaskPath() {
        return new File(this.getAppJavaPath(), PATH_APP_TASK);
    }

    public File getAppMigrationPath() {
        return new File(this.getAppPath(), FileUtils.getPath(this.getResourcesPath(), PATH_MIGRATION));
    }

    public File getConstantPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_CONSTANT));
    }

    public File getConstantJavaPath() {
        return new File(this.getConstantPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_CONSTANT));
    }

    public File getDaoPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_DAO));
    }

    public File getDaoJavaPath() {
        return new File(this.getDaoPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_DAO));
    }

    public File getDaoConvertorPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_CONVERTOR);
    }

    public File getDaoHelperPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_HELPER);
    }

    public File getDaoManagerPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_MANAGER);
    }

    public File getDaoMapperPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_MAPPER);
    }

    public File getDaoModelPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_MODEL);
    }

    public File getDaoRepositoryPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_REPOSITORY);
    }

    public File getDaoTabPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_TAB);
    }

    public File getDaoTabImplPath() {
        return new File(this.getDaoJavaPath(), PATH_DAO_TAB_IMPL);
    }

    public File getEntityPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_ENTITY));
    }

    public File getEntityJavaPath() {
        return new File(this.getEntityPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_ENTITY));
    }

    public File getEnumsPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_ENUMS));
    }

    public File getEnumsJavaPath() {
        return new File(this.getEnumsPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_ENUMS));
    }

    public File getFeignPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_FEIGN));
    }

    public File getFeignJavaPath() {
        return new File(this.getFeignPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_FEIGN));
    }

    public File getFeignApiPath() {
        return new File(this.getFeignJavaPath(), PATH_FEIGN_API);
    }

    public File getFeignDTOPath() {
        return new File(this.getFeignJavaPath(), PATH_FEIGN_DTO);
    }

    public File getFeignVOPath() {
        return new File(this.getFeignJavaPath(), PATH_FEIGN_VO);
    }

    public File getInterfacesPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_INTERFACES));
    }

    public File getInterfacesJavaPath() {
        return new File(this.getInterfacesPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_INTERFACES));
    }

    public File getGenericPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_GENERIC));
    }

    public File getGenericJavaPath() {
        return new File(this.getGenericPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_GENERIC));
    }

    public File getWebPath() {
        return new File(this.getRootPath(), String.format("%s-%s", this.getServiceName(), PATH_WEB));
    }

    public File getWebJavaPath() {
        return new File(this.getWebPath(), FileUtils.getPath(this.getJavaPath(), this.getModulePath(), PATH_WEB));
    }

    public File getWebResourcesPath() {
        return new File(this.getWebPath(), this.getResourcesPath());
    }

    public File getWebTestPath() {
        return new File(this.getWebPath(), FileUtils.getPath(this.getTestPath(), this.getModulePath(), PATH_WEB));
    }

    public File getRootGitIgnoreFile() {
        return new File(this.getRootPath(), FILE_GIT_IGNORE);
    }

    public File getRootPomFile() {
        return new File(this.getRootPath(), FILE_POM);
    }

    public File getAppGitIgnoreFile() {
        return new File(this.getAppPath(), FILE_GIT_IGNORE);
    }

    public File getAppPomFile() {
        return new File(this.getAppPath(), FILE_POM);
    }

    public File getAppMigrationFile() {
        return new File(this.getAppMigrationPath(), FILE_MIGRATION);
    }

    public File getDaoGitIgnoreFile() {
        return new File(this.getDaoPath(), FILE_GIT_IGNORE);
    }

    public File getDaoPomFile() {
        return new File(this.getDaoPath(), FILE_POM);
    }

    public File getConstantGitIgnoreFile() {
        return new File(this.getConstantPath(), FILE_GIT_IGNORE);
    }

    public File getConstantPomFile() {
        return new File(this.getConstantPath(), FILE_POM);
    }

    public File getEntityGitIgnoreFile() {
        return new File(this.getEntityPath(), FILE_GIT_IGNORE);
    }

    public File getEntityPomFile() {
        return new File(this.getEntityPath(), FILE_POM);
    }

    public File getEnumsGitIgnoreFile() {
        return new File(this.getEnumsPath(), FILE_GIT_IGNORE);
    }

    public File getEnumsPomFile() {
        return new File(this.getEnumsPath(), FILE_POM);
    }

    public File getFeignGitIgnoreFile() {
        return new File(this.getFeignPath(), FILE_GIT_IGNORE);
    }

    public File getFeignPomFile() {
        return new File(this.getFeignPath(), FILE_POM);
    }

    public File getInterfacesGitIgnoreFile() {
        return new File(this.getInterfacesPath(), FILE_GIT_IGNORE);
    }

    public File getInterfacesPomFile() {
        return new File(this.getInterfacesPath(), FILE_POM);
    }

    public File getGenericGitIgnoreFile() {
        return new File(this.getGenericPath(), FILE_GIT_IGNORE);
    }

    public File getGenericPomFile() {
        return new File(this.getGenericPath(), FILE_POM);
    }

    public File getGenericGeneratorFile() {
        return new File(this.getGenericJavaPath(), String.format(FILE_GENERATOR, this.getClassName()));
    }

    public File getWebGitIgnoreFile() {
        return new File(this.getWebPath(), FILE_GIT_IGNORE);
    }

    public File getWebPomFile() {
        return new File(this.getWebPath(), FILE_POM);
    }

    public File getWebApplicationFile() {
        return new File(this.getWebJavaPath(), String.format(FILE_APPLICATION, this.getClassName()));
    }

    public File getWebYmlFile() {
        return new File(this.getWebResourcesPath(), FILE_YML);
    }

    public File getWebYmlDevFile() {
        return new File(this.getWebResourcesPath(), FILE_YML_DEV);
    }

    public File getWebYmlFatFile() {
        return new File(this.getWebResourcesPath(), FILE_YML_FAT);
    }

    public File getWebYmlUatFile() {
        return new File(this.getWebResourcesPath(), FILE_YML_UAT);
    }

    public File getWebYmlProFile() {
        return new File(this.getWebResourcesPath(), FILE_YML_PRO);
    }

    public File getWebYmlLocalFile() {
        return new File(this.getWebResourcesPath(), FILE_YML_LOCAL);
    }

    public File getTestFile() {
        return new File(this.getWebTestPath(), String.format(FILE_TEST, this.getClassName()));
    }

}
