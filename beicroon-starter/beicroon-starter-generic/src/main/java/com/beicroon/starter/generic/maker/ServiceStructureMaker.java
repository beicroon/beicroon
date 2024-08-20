package com.beicroon.starter.generic.maker;

import com.beicroon.starter.generic.content.application.GenericApplicationContent;
import com.beicroon.starter.generic.content.application.WebApplicationContent;
import com.beicroon.starter.generic.content.ignore.GitIgnoreContent;
import com.beicroon.starter.generic.content.migration.MigrationContent;
import com.beicroon.starter.generic.content.pom.*;
import com.beicroon.starter.generic.content.yml.*;
import com.beicroon.starter.generic.manager.FileManager;
import com.beicroon.starter.generic.manager.PackageManager;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ServiceStructureMaker {

    public static void generic(File rootPath, String moduleName, String moduleVersion, String basePackage, int serverPort, int redisDB) {
        System.out.println("模块初始开始");

        String filename = Arrays.stream(moduleName.split("-"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());

        PackageManager packageManager = new PackageManager(basePackage);

        FileManager fileManager = new FileManager(rootPath, moduleName, packageManager);

        FileUtils.writeFileIfNotExists(fileManager.getModuleGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getModulePomFile(), ModulePomContent.getContent(moduleName, moduleVersion));
        FileUtils.writeFileIfNotExists(fileManager.getDaoGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getDaoPomFile(), DaoPomContent.getContent(moduleName, moduleVersion));
        FileUtils.writeFileIfNotExists(fileManager.getEntityGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getEntityPomFile(), EntityPomContent.getContent(moduleName, moduleVersion));
        FileUtils.writeFileIfNotExists(fileManager.getGenericGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getGenericPomFile(), GenericPomContent.getContent(moduleName, moduleVersion));
        FileUtils.writeFileIfNotExists(fileManager.getGenericApplicationFile(filename), GenericApplicationContent.getContent(filename, moduleName, basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getWebPomFile(), WebPomContent.getContent(moduleName, moduleVersion));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationFile(filename), WebApplicationContent.getContent(filename, basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationYmlFile(), ApplicationYmlContent.getContent(serverPort, redisDB));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationDevYmlFile(), ApplicationDevYmlContent.getContent(basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationFatYmlFile(), ApplicationFatYmlContent.getContent(basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationUatYmlFile(), ApplicationUatYmlContent.getContent(basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationProYmlFile(), ApplicationProYmlContent.getContent(basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getWebApplicationLocalYmlFile(), ApplicationLocalYmlContent.getContent(basePackage));
        FileUtils.writeFileIfNotExists(fileManager.getMigrationFile(moduleVersion), MigrationContent.getContent(moduleName));

        System.out.println("模块初始结束");
    }

}
