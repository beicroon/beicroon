package com.beicroon.starter.generic.maker;

import com.beicroon.construct.utils.FileUtils;
import com.beicroon.starter.generic.content.GitIgnoreContent;
import com.beicroon.starter.generic.content.pom.*;
import com.beicroon.starter.generic.structure.GenericStructure;

public class CommonStructureMaker {

    public static void generic(GenericStructure structure) {
        System.out.println("模块初始开始");

        FileUtils.mkdir(structure.getRootPath());
        FileUtils.writeIfNotExists(structure.getRootGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getRootPomFile(), CommonRootPomContent.getContent(structure));

        FileUtils.mkdir(structure.getConstantPath());
        FileUtils.writeIfNotExists(structure.getConstantGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getConstantPomFile(), ConstantPomContent.getContent(structure));
        FileUtils.mkdir(structure.getConstantJavaPath());

        FileUtils.mkdir(structure.getEnumsPath());
        FileUtils.writeIfNotExists(structure.getEnumsGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getEnumsPomFile(), EnumsPomContent.getContent(structure));
        FileUtils.mkdir(structure.getEnumsJavaPath());

        FileUtils.mkdir(structure.getRpcPath());
        FileUtils.writeIfNotExists(structure.getRpcGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getRpcPomFile(), RpcPomContent.getContent(structure));
        FileUtils.mkdir(structure.getRpcJavaPath());
        FileUtils.mkdir(structure.getRpcFeignPath());
        FileUtils.mkdir(structure.getRpcDubboPath());
        FileUtils.mkdir(structure.getRpcDTOPath());
        FileUtils.mkdir(structure.getRpcVOPath());

        FileUtils.mkdir(structure.getInterfacesPath());
        FileUtils.writeIfNotExists(structure.getInterfacesGitIgnoreFile(), GitIgnoreContent.getContent());
        FileUtils.writeIfNotExists(structure.getInterfacesPomFile(), InterfacesPomContent.getContent(structure));
        FileUtils.mkdir(structure.getInterfacesJavaPath());

        System.out.println("模块初始结束");
    }

}
