package com.beicroon.middle.tenant.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

@Data
@ConfigurationProperties(prefix = "beicroon.tenant")
public class TenantProperty {

    private Boolean enable = true;

    private Set<String> ignoreUrls = Collections.emptySet();

    private Set<String> ignoreTables = Collections.emptySet();

}
