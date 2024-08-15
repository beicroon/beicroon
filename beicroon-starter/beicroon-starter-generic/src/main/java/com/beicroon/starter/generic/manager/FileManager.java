package com.beicroon.starter.generic.manager;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.utils.FileUtils;
import lombok.Getter;

import java.io.File;

public class FileManager {

    public static final String SUFFIX_CONVERTOR = "Convertor.java";

    public static final String SUFFIX_HELPER = "Helper.java";

    public static final String SUFFIX_MANAGER = "Manager.java";

    public static final String SUFFIX_MAPPER = "Mapper.java";

    public static final String SUFFIX_MODEL = "Model.java";

    public static final String SUFFIX_REPOSITORY = "Repository.java";

    public static final String SUFFIX_CREATE_DTO = "CreateDTO.java";

    public static final String SUFFIX_UPDATE_DTO = "UpdateDTO.java";

    public static final String SUFFIX_QUERY_DTO = "QueryDTO.java";

    public static final String SUFFIX_BASE_VO = "BaseVO.java";

    public static final String SUFFIX_DETAIL_VO = "DetailVO.java";

    public static final String SUFFIX_PAGE_VO = "PageVO.java";

    public static final String SUFFIX_CONTROLLER_ADMIN = "Admin%sController.java";

    public static final String FILENAME_SERVICE = "I%sService.java";

    public static final String FILENAME_SERVICE_IMPL = "%sService.java";

    @Getter
    private final File rootPath;

    private final String serviceName;

    private final PackageManager packageManager;

    private File daoPath;

    private File entityPath;

    private File genericPath;

    private File webPath;

    private File convertorPath;

    private File helperPath;

    private File managerPath;

    private File mapperPath;

    private File modelPath;

    private File repositoryPath;

    private File controllerAdminPath;

    private File controllerHomePath;

    private File servicePath;

    private File serviceImplPath;

    public FileManager(File rootPath, String serviceName, PackageManager packageManager) {
        this.rootPath = rootPath;

        this.serviceName = serviceName;

        this.packageManager = packageManager;
    }

    public File getDaoPath() {
        if (this.daoPath == null) {
            this.daoPath = FileUtils.getDaoPath(this.getRootPath(), this.serviceName);
        }

        return this.daoPath;
    }

    public File getEntityPath() {
        if (this.entityPath == null) {
            this.entityPath = FileUtils.getEntityPath(this.getRootPath(), this.serviceName);
        }

        return this.entityPath;
    }

    public File getGenericPath() {
        if (this.genericPath == null) {
            this.genericPath = FileUtils.getGenericPath(this.getRootPath(), this.serviceName);
        }

        return this.genericPath;
    }

    public File getWebPath() {
        if (this.webPath == null) {
            this.webPath = FileUtils.getWebPath(this.getRootPath(), this.serviceName);
        }

        return this.webPath;
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

    public File getConverTorFile(Table table) {
        return new File(this.getConvertorPath(), table.getFilename() + SUFFIX_CONVERTOR);
    }

    public File getHelperFile(Table table) {
        return new File(this.getHelperPath(), table.getFilename() + SUFFIX_HELPER);
    }

    public File getManagerFile(Table table) {
        return new File(this.getManagerPath(), table.getFilename() + SUFFIX_MANAGER);
    }

    public File getMapperFile(Table table) {
        return new File(this.getMapperPath(), table.getFilename() + SUFFIX_MAPPER);
    }

    public File getModelFile(Table table) {
        return new File(this.getModelPath(), table.getFilename() + SUFFIX_MODEL);
    }

    public File getRepositoryFile(Table table) {
        return new File(this.getRepositoryPath(), table.getFilename() + SUFFIX_REPOSITORY);
    }

    public File getCreateDTOFile(Table table) {
        return new File(this.getDTOPath(table),  table.getFilename() + SUFFIX_CREATE_DTO);
    }

    public File getUpdateDTOFile(Table table) {
        return new File(this.getDTOPath(table),  table.getFilename() + SUFFIX_UPDATE_DTO);
    }

    public File getQueryDTOFile(Table table) {
        return new File(this.getDTOPath(table),  table.getFilename() + SUFFIX_QUERY_DTO);
    }

    public File getBaseVOFile(Table table) {
        return new File(this.getVOPath(table),  table.getFilename() + SUFFIX_BASE_VO);
    }

    public File getDetailVOFile(Table table) {
        return new File(this.getVOPath(table),  table.getFilename() + SUFFIX_DETAIL_VO);
    }

    public File getPageVOFile(Table table) {
        return new File(this.getVOPath(table),  table.getFilename() + SUFFIX_PAGE_VO);
    }

    public File getControllerAdminFile(Table table) {
        return new File(this.getControllerAdminPath(),  String.format(SUFFIX_CONTROLLER_ADMIN, table.getFilename()));
    }

    public File getServiceFile(Table table) {
        return new File(this.getServicePath(),  String.format(FILENAME_SERVICE, table.getFilename()));
    }

    public File getServiceImplFile(Table table) {
        return new File(this.getServiceImplPath(),  String.format(FILENAME_SERVICE_IMPL, table.getFilename()));
    }

}
