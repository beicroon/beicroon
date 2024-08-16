package com.beicroon.starter.generic.maker;

import com.beicroon.starter.generic.content.ignore.GitIgnoreContent;
import com.beicroon.starter.generic.content.pom.ModulePomContent;
import com.beicroon.starter.generic.manager.FileManager;
import com.beicroon.starter.generic.manager.PackageManager;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;

public class ServiceStructureMaker {

    public static void generic(File rootPath, String moduleName, String moduleVersion, String basePackage, int serverPort, int redisDB) {
        System.out.println("模块初始开始");

        PackageManager packageManager = new PackageManager(basePackage);

        FileManager fileManager = new FileManager(rootPath, moduleName, packageManager);

        FileUtils.writeFileIfNotExists(fileManager.getModuleGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeFileIfNotExists(fileManager.getModulePomFile(), ModulePomContent.getContent(moduleName, moduleVersion));
//        fileManager.getDaoPath();
//        fileManager.getEntityPath();
//        fileManager.getGenericPath();
//        fileManager.getWebPath();

        System.out.println("模块初始结束");
    }

}
