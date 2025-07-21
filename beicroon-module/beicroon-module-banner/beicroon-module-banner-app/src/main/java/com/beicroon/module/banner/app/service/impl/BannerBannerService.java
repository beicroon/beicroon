package com.beicroon.module.banner.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.module.banner.app.service.IBannerBannerService;
import com.beicroon.module.banner.dao.convertor.BannerBannerConvertor;
import com.beicroon.module.banner.dao.manager.BannerBannerManager;
import com.beicroon.module.banner.dao.model.BannerBannerModel;
import com.beicroon.module.banner.dao.repository.BannerBannerRepository;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerCreateDTO;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerUpdateDTO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerDetailVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerPageVO;
import com.beicroon.provider.mybatisplus.utils.WrapperUtils;
import com.beicroon.starter.dao.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BannerBannerService implements IBannerBannerService {

    @Resource
    private DBManager dbManager;

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
    public List<BannerBannerBaseVO> listEffective(BannerBannerQueryDTO dto) {
        LambdaQueryWrapper<BannerBannerModel> query = this.bannerBannerRepository.newLambdaQueryWrapper(dto);

        WrapperUtils.effective(query, BannerBannerModel::getBannerEffectAt, BannerBannerModel::getBannerExpiredAt);

        List<BannerBannerModel> list = this.bannerBannerRepository.list(query);

        list.sort(Comparator.comparing(BannerBannerModel::getBannerSorting));

        return this.bannerBannerConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<BannerBannerPageVO> page(BannerBannerQueryDTO dto) {
        PageInfo<BannerBannerModel> page = this.bannerBannerRepository.page(dto);

        if (page.isEmpty()) {
            return new PageInfo<>(dto.getPageInfo());
        }

        return this.bannerBannerConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(BannerBannerCreateDTO dto) {
        BannerBannerModel creator = this.bannerBannerConvertor.toCreator(dto);

        return this.bannerBannerRepository.insert(creator);
    }

    @Override
    public Boolean update(BannerBannerUpdateDTO dto) {
        BannerBannerModel model = this.bannerBannerRepository.getByIdOrError(
                dto.getId(), "轮播图不存在或已删除"
        );

        BannerBannerModel updater = this.bannerBannerConvertor.toUpdater(model, dto);

        return this.bannerBannerRepository.updateById(updater);
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
