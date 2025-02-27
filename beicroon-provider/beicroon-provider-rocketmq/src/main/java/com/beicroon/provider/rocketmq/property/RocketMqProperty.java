package com.beicroon.provider.rocketmq.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties("rocketmq")
public class RocketMqProperty implements Serializable {

    private String name;

    private String serverAddress;

    private String namespace;

    private String groupId;

    private boolean consumerEnabled;

}
