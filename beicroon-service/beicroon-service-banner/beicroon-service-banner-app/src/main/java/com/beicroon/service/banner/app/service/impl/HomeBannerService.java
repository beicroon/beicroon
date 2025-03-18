package com.beicroon.service.banner.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.banner.app.service.IHomeBannerService;
import com.beicroon.service.banner.dao.convertor.HomeBannerConvertor;
import com.beicroon.service.banner.dao.manager.HomeBannerManager;
import com.beicroon.service.banner.dao.model.HomeBannerModel;
import com.beicroon.service.banner.dao.repository.HomeBannerRepository;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerCreateDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerQueryDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerUpdateDTO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerBaseVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerDetailVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerPageVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeBannerService implements IHomeBannerService {

    @Resource
    private HomeBannerManager homeBannerManager;

    @Resource
    private HomeBannerConvertor homeBannerConvertor;

    @Resource
    private HomeBannerRepository homeBannerRepository;

    @Override
    public List<TabVO> tab(HomeBannerQueryDTO dto) {
        return this.homeBannerRepository.tab(dto);
    }

    @Override
    public List<HomeBannerBaseVO> list(HomeBannerQueryDTO dto) {
        List<HomeBannerModel> list = this.homeBannerRepository.list(dto);

        return this.homeBannerConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<HomeBannerPageVO> page(HomeBannerQueryDTO dto) {
        PageInfo<HomeBannerModel> page = this.homeBannerRepository.page(dto);

        return this.homeBannerConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(HomeBannerCreateDTO dto) {
        HomeBannerModel model = this.homeBannerConvertor.toCreator(dto);

        return this.homeBannerRepository.insert(model);
    }

    @Override
    public Boolean update(HomeBannerUpdateDTO dto) {
        HomeBannerModel model = this.homeBannerConvertor.toUpdater(dto);

        return this.homeBannerRepository.updateById(model);
    }

    @Override
    public HomeBannerBaseVO show(Long id) {
        HomeBannerModel model = this.homeBannerRepository.getById(id);

        return this.homeBannerConvertor.toBaseVO(model);
    }

    @Override
    public HomeBannerDetailVO detail(Long id) {
        HomeBannerModel model = this.homeBannerRepository.getById(id);

        return this.homeBannerConvertor.toDetailVO(model);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.homeBannerRepository.removeByIds(dto.getIds());
    }

}
