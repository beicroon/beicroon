package com.beicroon.module.banner.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.module.banner.dao.mapper.BannerBannerMapper;
import com.beicroon.module.banner.dao.model.BannerBannerModel;
import com.beicroon.module.banner.dao.tab.BannerBannerTabHandler;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.starter.dao.repository.GenericPageRepository;
import com.beicroon.starter.dao.repository.impl.MybatisplusPageRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerBannerRepository extends MybatisplusPageRepository<BannerBannerQueryDTO, BannerBannerMapper, BannerBannerModel> implements GenericPageRepository<BannerBannerQueryDTO, BannerBannerModel> {

    @Resource
    private List<BannerBannerTabHandler> tabHandlers;

    @Override
    public List<BannerBannerTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public void fillQueryWrapper(BannerBannerQueryDTO dto, QueryWrapper<BannerBannerModel> wrapper) {

    }

}
