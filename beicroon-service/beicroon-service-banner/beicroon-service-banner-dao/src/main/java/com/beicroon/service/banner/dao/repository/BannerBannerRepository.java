package com.beicroon.service.banner.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.banner.dao.mapper.BannerBannerMapper;
import com.beicroon.service.banner.dao.model.BannerBannerModel;
import com.beicroon.service.banner.dao.tab.BannerBannerTabHandler;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
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
    public IQueryWrapper<BannerBannerModel> fillQueryWrapper(BannerBannerQueryDTO dto, IQueryWrapper<BannerBannerModel> wrapper) {
        return wrapper;
    }

}
