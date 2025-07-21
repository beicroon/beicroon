package com.beicroon.module.base.dao.manager;

import com.beicroon.module.base.dao.convertor.BaseConfigConvertor;
import com.beicroon.module.base.dao.repository.BaseConfigRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseConfigManager {

    @Resource
    private BaseConfigConvertor baseConfigConvertor;

    @Resource
    private BaseConfigRepository baseConfigRepository;

}
