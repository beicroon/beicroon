package com.beicroon.starter.generic.manager;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;

public class FileManager {

    public static final String SUFFIX_GIT_IGNORE = ".gitignore";

    public static final String SUFFIX_POM = "pom.xml";

    public static final String SUFFIX_CONVERTOR = "%sConvertor.java";

    public static final String SUFFIX_HELPER = "%sHelper.java";

    public static final String SUFFIX_MANAGER = "%sManager.java";

    public static final String SUFFIX_MAPPER = "%sMapper.java";

    public static final String SUFFIX_MODEL = "%sModel.java";

    public static final String SUFFIX_REPOSITORY = "%sRepository.java";

    public static final String SUFFIX_MAPPER_XML = "%sMapper.xml";

    public static final String SUFFIX_CREATE_DTO = "%sCreateDTO.java";

    public static final String SUFFIX_UPDATE_DTO = "%sUpdateDTO.java";

    public static final String SUFFIX_QUERY_DTO = "%sQueryDTO.java";

    public static final String SUFFIX_BASE_VO = "%sBaseVO.java";

    public static final String SUFFIX_DETAIL_VO = "%sDetailVO.java";

    public static final String SUFFIX_PAGE_VO = "%sPageVO.java";

    public static final String SUFFIX_GENERIC_APPLICATION = "%sGenerator.java";

    public static final String SUFFIX_WEB_APPLICATION = "%sApplication.java";

    public static final String SUFFIX_YML_APPLICATION = "application.yml";

    public static final String SUFFIX_YML_APPLICATION_DEV = "application-dev.yml";

    public static final String SUFFIX_YML_APPLICATION_FAT = "application-fat.yml";

    public static final String SUFFIX_YML_APPLICATION_UAT = "application-uat.yml";

    public static final String SUFFIX_YML_APPLICATION_PRO = "application-pro.yml";

    public static final String SUFFIX_YML_APPLICATION_LOCAL = "application-local.yml";

    public static final String SUFFIX_MIGRATION = "V%s_feature.sql";

    public static final String SUFFIX_CONTROLLER_ADMIN = "Admin%sController.java";

    public static final String FILENAME_SERVICE = "I%sService.java";

    public static final String FILENAME_SERVICE_IMPL = "%sService.java";

    private final File rootPath;

    private final String moduleName;

    private final PackageManager packageManager;

    private File daoPath;

    private File entityPath;

    private File genericPath;

    private File genericApplicationPath;

    private File webPath;

    private File webApplicationPath;

    private File webResourcesPath;

    private File webMigrationPath;

    private File convertorPath;

    private File helperPath;

    private File managerPath;

    private File mapperPath;

    private File modelPath;

    private File repositoryPath;

    private File mapperXmlPath;

    private File controllerAdminPath;

    private File servicePath;

    private File serviceImplPath;

    public FileManager(File rootPath, String moduleName, PackageManager packageManager) {
        this.rootPath = rootPath;

        this.moduleName = moduleName;

        this.packageManager = packageManager;
    }

    public File getRootPath() {
        return FileUtils.mkdir(this.rootPath);
    }

    public File getDaoPath() {
        if (this.daoPath == null) {
            this.daoPath = FileUtils.getDaoPath(this.getRootPath(), this.moduleName);
        }

        return this.daoPath;
    }

    public File getEntityPath() {
        if (this.entityPath == null) {
            this.entityPath = FileUtils.getEntityPath(this.getRootPath(), this.moduleName);
        }

        return this.entityPath;
    }

    public File getGenericPath() {
        if (this.genericPath == null) {
            this.genericPath = FileUtils.getGenericPath(this.getRootPath(), this.moduleName);
        }

        return this.genericPath;
    }

    public File getGenericApplicationPath() {
        if (this.genericApplicationPath == null) {
            this.genericApplicationPath = FileUtils.getGenericApplicationPath(this.getGenericPath(), this.packageManager.getBasePackages());
        }

        return this.genericApplicationPath;
    }

    public File getWebPath() {
        if (this.webPath == null) {
            this.webPath = FileUtils.getWebPath(this.getRootPath(), this.moduleName);
        }

        return this.webPath;
    }

    public File getWebApplicationPath() {
        if (this.webApplicationPath == null) {
            this.webApplicationPath = FileUtils.getWebApplicationPath(this.getWebPath(), this.packageManager.getBasePackages());
        }

        return this.webApplicationPath;
    }

    public File getWebResourcesPath() {
        if (this.webResourcesPath == null) {
            this.webResourcesPath = FileUtils.getWebResourcesPath(this.getWebPath());
        }

        return this.webResourcesPath;
    }

    public File getWebMigrationPath() {
        if (this.webMigrationPath == null) {
            this.webMigrationPath = FileUtils.getWebMigrationPath(this.getWebResourcesPath());
        }

        return this.webMigrationPath;
    }

    public File getDTOPath(Table table) {
        return FileUtils.getDTOPath(this.getEntityPath(), table, this.packageManager.getBasePackages());
    }

    public File getVOPath(Table table) {
        return FileUtils.getVOPath(this.getEntityPath(), table, this.packageManager.getBasePackages());
    }

    public File getConvertorPath() {
        if (this.convertorPath == null) {
            this.convertorPath = FileUtils.getConvertorPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.convertorPath;
    }

    public File getHelperPath() {
        if (this.helperPath == null) {
            this.helperPath = FileUtils.getHelperPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.helperPath;
    }

    public File getManagerPath() {
        if (this.managerPath == null) {
            this.managerPath = FileUtils.getManagerPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.managerPath;
    }

    public File getMapperPath() {
        if (this.mapperPath == null) {
            this.mapperPath = FileUtils.getMapperPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.mapperPath;
    }

    public File getModelPath() {
        if (this.modelPath == null) {
            this.modelPath = FileUtils.getModelPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.modelPath;
    }

    public File getRepositoryPath() {
        if (this.repositoryPath == null) {
            this.repositoryPath = FileUtils.getRepositoryPath(this.getDaoPath(), this.packageManager.getBasePackages());
        }

        return this.repositoryPath;
    }

    public File getMapperXmlPath() {
        if (this.mapperXmlPath == null) {
            this.mapperXmlPath = FileUtils.getMapperXmlPath(this.getDaoPath());
        }

        return this.mapperXmlPath;
    }

    public File getControllerAdminPath() {
        if (this.controllerAdminPath == null) {
            this.controllerAdminPath = FileUtils.getControllerAdminPath(this.getWebPath(), this.packageManager.getBasePackages());
        }

        return this.controllerAdminPath;
    }

    public File getControllerHomePath() {
        if (this.controllerAdminPath == null) {
            this.controllerAdminPath = FileUtils.getControllerAdminPath(this.getWebPath(), this.packageManager.getBasePackages());
        }

        return this.controllerAdminPath;
    }

    public File getServicePath() {
        if (this.servicePath == null) {
            this.servicePath = FileUtils.getServicePath(this.getWebPath(), this.packageManager.getBasePackages());
        }

        return this.servicePath;
    }

    public File getServiceImplPath() {
        if (this.serviceImplPath == null) {
            this.serviceImplPath = FileUtils.getServiceImplPath(this.getWebPath(), this.packageManager.getBasePackages());
        }

        return this.serviceImplPath;
    }

    public File getModuleGitIgnoreFile() {
        return new File(this.getRootPath(), SUFFIX_GIT_IGNORE);
    }

    public File getModulePomFile() {
        return new File(this.getRootPath(), SUFFIX_POM);
    }

    public File getDaoGitIgnoreFile() {
        return new File(this.getDaoPath(), SUFFIX_GIT_IGNORE);
    }

    public File getDaoPomFile() {
        return new File(this.getDaoPath(), SUFFIX_POM);
    }

    public File getEntityGitIgnoreFile() {
        return new File(this.getEntityPath(), SUFFIX_GIT_IGNORE);
    }

    public File getEntityPomFile() {
        return new File(this.getEntityPath(), SUFFIX_POM);
    }

    public File getGenericGitIgnoreFile() {
        return new File(this.getGenericPath(), SUFFIX_GIT_IGNORE);
    }

    public File getGenericPomFile() {
        return new File(this.getGenericPath(), SUFFIX_POM);
    }

    public File getGenericApplicationFile(String filename) {
        return new File(this.getGenericApplicationPath(), String.format(SUFFIX_GENERIC_APPLICATION, filename));
    }

    public File getWebGitIgnoreFile() {
        return new File(this.getWebPath(), SUFFIX_GIT_IGNORE);
    }

    public File getWebPomFile() {
        return new File(this.getWebPath(), SUFFIX_POM);
    }

    public File getWebApplicationFile(String filename) {
        return new File(this.getWebApplicationPath(), String.format(SUFFIX_WEB_APPLICATION, filename));
    }

    public File getWebApplicationYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION);
    }

    public File getWebApplicationDevYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION_DEV);
    }

    public File getWebApplicationFatYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION_FAT);
    }

    public File getWebApplicationUatYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION_UAT);
    }

    public File getWebApplicationProYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION_PRO);
    }

    public File getWebApplicationLocalYmlFile() {
        return new File(this.getWebResourcesPath(), SUFFIX_YML_APPLICATION_LOCAL);
    }

    public File getMigrationFile(String moduleVersion) {
        return new File(this.getWebMigrationPath(), String.format(SUFFIX_MIGRATION, moduleVersion));
    }

    public File getConverTorFile(Table table) {
        return new File(this.getConvertorPath(), String.format(SUFFIX_CONVERTOR, table.getFilename()));
    }

    public File getHelperFile(Table table) {
        return new File(this.getHelperPath(), String.format(SUFFIX_HELPER, table.getFilename()));
    }

    public File getManagerFile(Table table) {
        return new File(this.getManagerPath(), String.format(SUFFIX_MANAGER, table.getFilename()));
    }

    public File getMapperFile(Table table) {
        return new File(this.getMapperPath(), String.format(SUFFIX_MAPPER, table.getFilename()));
    }

    public File getModelFile(Table table) {
        return new File(this.getModelPath(), String.format(SUFFIX_MODEL, table.getFilename()));
    }

    public File getRepositoryFile(Table table) {
        return new File(this.getRepositoryPath(), String.format(SUFFIX_REPOSITORY, table.getFilename()));
    }

    public File getMapperXmlFile(Table table) {
        return new File(this.getMapperXmlPath(), String.format(SUFFIX_MAPPER_XML, table.getFilename()));
    }

    public File getCreateDTOFile(Table table) {
        return new File(this.getDTOPath(table), String.format(SUFFIX_CREATE_DTO, table.getFilename()));
    }

    public File getUpdateDTOFile(Table table) {
        return new File(this.getDTOPath(table), String.format(SUFFIX_UPDATE_DTO, table.getFilename()));
    }

    public File getQueryDTOFile(Table table) {
        return new File(this.getDTOPath(table), String.format(SUFFIX_QUERY_DTO, table.getFilename()));
    }

    public File getBaseVOFile(Table table) {
        return new File(this.getVOPath(table), String.format(SUFFIX_BASE_VO, table.getFilename()));
    }

    public File getDetailVOFile(Table table) {
        return new File(this.getVOPath(table), String.format(SUFFIX_DETAIL_VO, table.getFilename()));
    }

    public File getPageVOFile(Table table) {
        return new File(this.getVOPath(table), String.format(SUFFIX_PAGE_VO, table.getFilename()));
    }

    public File getControllerAdminFile(Table table) {
        return new File(this.getControllerAdminPath(), String.format(SUFFIX_CONTROLLER_ADMIN, table.getFilename()));
    }

    public File getServiceFile(Table table) {
        return new File(this.getServicePath(), String.format(FILENAME_SERVICE, table.getFilename()));
    }

    public File getServiceImplFile(Table table) {
        return new File(this.getServiceImplPath(), String.format(FILENAME_SERVICE_IMPL, table.getFilename()));
    }

}
