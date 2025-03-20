package com.beicroon.service.banner.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.banner.app.service.IBannerBannerService;
import com.beicroon.service.banner.dao.convertor.BannerBannerConvertor;
import com.beicroon.service.banner.dao.manager.BannerBannerManager;
import com.beicroon.service.banner.dao.model.BannerBannerModel;
import com.beicroon.service.banner.dao.repository.BannerBannerRepository;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerCreateDTO;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerUpdateDTO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerDetailVO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerPageVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerBannerService implements IBannerBannerService {

    @Resource
    private BannerBannerManager bannerBannerManager;

    @Resource
    private BannerBannerConvertor bannerBannerConvertor;

    @Resource
    private BannerBannerRepository bannerBannerRepository;

    @Override
    public List<TabVO> tab(BannerBannerQueryDTO dto) {
        return this.bannerBannerRepository.tab(dto);
    }

    @Override
    public List<BannerBannerBaseVO> list(BannerBannerQueryDTO dto) {
        List<BannerBannerModel> list = this.bannerBannerRepository.list(dto);

        return this.bannerBannerConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<BannerBannerPageVO> page(BannerBannerQueryDTO dto) {
        PageInfo<BannerBannerModel> page = this.bannerBannerRepository.page(dto);

        return this.bannerBannerConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(BannerBannerCreateDTO dto) {
        BannerBannerModel model = this.bannerBannerConvertor.toCreator(dto);

        return this.bannerBannerRepository.insert(model);
    }

    @Override
    public Boolean update(BannerBannerUpdateDTO dto) {
        BannerBannerModel model = this.bannerBannerConvertor.toUpdater(dto);

        return this.bannerBannerRepository.updateById(model);
    }

    @Override
    public BannerBannerBaseVO show(Long id) {
        BannerBannerModel model = this.bannerBannerRepository.getById(id);

        return this.bannerBannerConvertor.toBaseVO(model);
    }

    @Override
    public BannerBannerDetailVO detail(Long id) {
        BannerBannerModel model = this.bannerBannerRepository.getById(id);

        return this.bannerBannerConvertor.toDetailVO(model);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.bannerBannerRepository.removeByIds(dto.getIds());
    }

}
