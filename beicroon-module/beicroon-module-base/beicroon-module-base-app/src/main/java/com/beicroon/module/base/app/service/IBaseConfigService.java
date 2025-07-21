package com.beicroon.module.base.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigCreateDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigQueryDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigUpdateDTO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigBaseVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigDetailVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigPageVO;

import java.util.List;

public interface IBaseConfigService {

    List<TabVO> tab(BaseConfigQueryDTO dto);

    List<BaseConfigBaseVO> list(BaseConfigQueryDTO dto);

    PageInfo<BaseConfigPageVO> page(BaseConfigQueryDTO dto);

    Boolean create(BaseConfigCreateDTO dto);

    Boolean update(BaseConfigUpdateDTO dto);

    BaseConfigBaseVO show(Long id);

    BaseConfigDetailVO detail(Long id);

    BaseConfigDetailVO detailByKey(String key);

    Boolean remove(IdsDTO dto);

}
