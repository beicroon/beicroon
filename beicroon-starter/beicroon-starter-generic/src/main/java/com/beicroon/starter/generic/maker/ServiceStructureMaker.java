package com.beicroon.starter.generic.maker;

import com.beicroon.construct.utils.FileUtils;
import com.beicroon.starter.generic.content.GitIgnoreContent;
import com.beicroon.starter.generic.content.pom.*;
import com.beicroon.starter.generic.structure.GenericStructure;

public class ServiceStructureMaker {

    public static void generic(GenericStructure structure) {
        System.out.println("模块初始开始");

        FileUtils.mkdir(structure.getRootPath());
        FileUtils.writeIfNotExists(structure.getRootGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getRootPomFile(), ServiceRootPomContent.getContent(structure));

        FileUtils.mkdir(structure.getAppPath());
        FileUtils.writeIfNotExists(structure.getAppGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getAppPomFile(), AppPomContent.getContent(structure));
        FileUtils.mkdir(structure.getAppJavaPath());
        FileUtils.mkdir(structure.getAppConsumerPath());
        FileUtils.mkdir(structure.getAppControllerAdminPath());
        FileUtils.mkdir(structure.getAppControllerHomePath());
        FileUtils.mkdir(structure.getAppEventPath());
        FileUtils.mkdir(structure.getAppListenerPath());
        FileUtils.mkdir(structure.getAppProducerPath());
        FileUtils.mkdir(structure.getAppServiceImplPath());
        FileUtils.mkdir(structure.getAppTaskPath());
        FileUtils.mkdir(structure.getAppMigrationPath());
        FileUtils.writeIfNotExists(structure.getAppMigrationFile(), MigrationPomContent.getContent(structure));

        FileUtils.mkdir(structure.getDaoPath());
        FileUtils.writeIfNotExists(structure.getDaoGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getDaoPomFile(), DaoPomContent.getContent(structure));
        FileUtils.mkdir(structure.getDaoConvertorPath());
        FileUtils.mkdir(structure.getDaoJavaPath());
        FileUtils.mkdir(structure.getDaoHelperPath());
        FileUtils.mkdir(structure.getDaoManagerPath());
        FileUtils.mkdir(structure.getDaoMapperPath());
        FileUtils.mkdir(structure.getDaoModelPath());
        FileUtils.mkdir(structure.getDaoRepositoryPath());
        FileUtils.mkdir(structure.getDaoTabPath());
        FileUtils.mkdir(structure.getDaoTabImplPath());

        FileUtils.mkdir(structure.getEntityPath());
        FileUtils.writeIfNotExists(structure.getEntityGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getEntityPomFile(), EntityPomContent.getContent(structure));
        FileUtils.mkdir(structure.getEntityJavaPath());

        FileUtils.mkdir(structure.getGenericPath());
        FileUtils.writeIfNotExists(structure.getGenericGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getGenericPomFile(), GenericPomContent.getContent(structure));
        FileUtils.mkdir(structure.getGenericJavaPath());
        FileUtils.writeIfNotExists(structure.getGenericGeneratorFile(), GeneratorContent.getContent(structure));

        FileUtils.mkdir(structure.getWebPath());
        FileUtils.writeIfNotExists(structure.getWebGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getWebPomFile(), WebPomContent.getContent(structure));
        FileUtils.mkdir(structure.getWebJavaPath());
        FileUtils.writeIfNotExists(structure.getWebApplicationFile(), ApplicationPomContent.getContent(structure));
        FileUtils.mkdir(structure.getWebResourcesPath());
        FileUtils.writeIfNotExists(structure.getWebYmlFile(), YmlPomContent.getContent(structure));
        FileUtils.writeIfNotExists(structure.getWebYmlDevFile(), YmlDevPomContent.getContent(structure));
        FileUtils.writeIfNotExists(structure.getWebYmlFatFile(), YmlFatPomContent.getContent(structure));
        FileUtils.writeIfNotExists(structure.getWebYmlUatFile(), YmlUatPomContent.getContent(structure));
        FileUtils.writeIfNotExists(structure.getWebYmlProFile(), YmlProPomContent.getContent(structure));
        FileUtils.writeIfNotExists(structure.getWebYmlLocalFile(), YmlLocalPomContent.getContent(structure));

        FileUtils.mkdir(structure.getWebTestPath());
        FileUtils.writeIfNotExists(structure.getTestFile(), TestPomContent.getContent(structure));

        System.out.println("模块初始结束");
    }

}
