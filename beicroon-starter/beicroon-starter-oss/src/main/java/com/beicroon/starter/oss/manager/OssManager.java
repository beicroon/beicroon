package com.beicroon.starter.oss.manager;

import com.beicroon.construct.oss.template.IOssTemplate;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OssManager {

    @Value("${beicroon.oss.name:localstorage}")
    private String ossName;

    @Resource
    private List<IOssTemplate> templates;

    private IOssTemplate ossTemplate;

    public IOssTemplate getOssTemplate() {
        if (this.ossTemplate != null) {
            return this.ossTemplate;
        }

        this.ossTemplate = this.templates.stream()
                .filter(ossTemplate -> ossTemplate.getName().equals(this.ossName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported oss name: " + this.ossName));

        return this.ossTemplate;
    }

    public String upload(byte[] bytes, String filename, String ext) {
        return this.getOssTemplate().upload(bytes, filename, ext);
    }

}
