package com.beicroon.starter.generic.manager;

import com.beicroon.starter.generic.utils.FileUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class PackageManager {

    private final String basePackage;

    private final List<String> basePackages;

    public PackageManager(String basePackage) {
        this.basePackage = basePackage;

        this.basePackages = Arrays.asList(basePackage.split("\\."));
    }

    public String getDaoPackage() {
        return String.format("%s.%s", this.basePackage, FileUtils.PACKAGE_DAO);
    }

}
