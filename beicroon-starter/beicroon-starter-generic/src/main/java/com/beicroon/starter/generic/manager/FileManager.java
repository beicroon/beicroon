package com.beicroon.starter.generic.manager;

import com.beicroon.starter.generic.utils.FileUtils;
import lombok.Getter;

import java.io.File;

public class FileManager {

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

}
