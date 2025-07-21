package com.beicroon.module.base.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.module.base.app.service.IBaseConfigService;
import com.beicroon.module.base.dao.convertor.BaseConfigConvertor;
import com.beicroon.module.base.dao.manager.BaseConfigManager;
import com.beicroon.module.base.dao.model.BaseConfigModel;
import com.beicroon.module.base.dao.repository.BaseConfigRepository;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigCreateDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigQueryDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigUpdateDTO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigBaseVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigDetailVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigPageVO;
import com.beicroon.starter.dao.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseConfigService implements IBaseConfigService {

    @Resource
    private DBManager dbManager;

    @Resource
    private BaseConfigManager baseConfigManager;

    @Resource
    private BaseConfigConvertor baseConfigConvertor;

    @Resource
    private BaseConfigRepository baseConfigRepository;

    @Override
    public List<TabVO> tab(BaseConfigQueryDTO dto) {
        return this.baseConfigRepository.tab(dto);
    }

    @Override
    public List<BaseConfigBaseVO> list(BaseConfigQueryDTO dto) {
        List<BaseConfigModel> list = this.baseConfigRepository.list(dto);

        return this.baseConfigConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<BaseConfigPageVO> page(BaseConfigQueryDTO dto) {
        PageInfo<BaseConfigModel> page = this.baseConfigRepository.page(dto);

        if (page.isEmpty()) {
            return new PageInfo<>(dto.getPageInfo());
        }

        return this.baseConfigConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(BaseConfigCreateDTO dto) {
        if (this.baseConfigRepository.existed(BaseConfigModel::getConfigKey, dto.getConfigKey())) {
            throw ExceptionUtils.business("配置键已存在");
        }

        BaseConfigModel creator = this.baseConfigConvertor.toCreator(dto);

        return this.baseConfigRepository.insert(creator);
    }

    @Override
    public Boolean update(BaseConfigUpdateDTO dto) {
        BaseConfigModel model = this.baseConfigRepository.getByIdOrError(
                dto.getId(), "配置不存在或已删除"
        );

        BaseConfigModel updater = this.baseConfigConvertor.toUpdater(model, dto);

        return this.baseConfigRepository.updateById(updater);
    }

    @Override
    public BaseConfigBaseVO show(Long id) {
        BaseConfigModel model = this.baseConfigRepository.getById(id);

        return this.baseConfigConvertor.toBaseVO(model);
    }

    @Override
    public BaseConfigDetailVO detail(Long id) {
        BaseConfigModel model = this.baseConfigRepository.getById(id);

        return this.baseConfigConvertor.toDetailVO(model);
    }

    @Override
    public BaseConfigDetailVO detailByKey(String key) {
        BaseConfigModel model = this.baseConfigRepository.first(BaseConfigModel::getConfigKey, key);

        return this.baseConfigConvertor.toDetailVO(model);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.baseConfigRepository.removeByIds(dto.getIds());
    }

}
