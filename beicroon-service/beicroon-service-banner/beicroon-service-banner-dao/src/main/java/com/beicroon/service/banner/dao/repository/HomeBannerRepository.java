package com.beicroon.service.banner.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.banner.dao.mapper.HomeBannerMapper;
import com.beicroon.service.banner.dao.model.HomeBannerModel;
import com.beicroon.service.banner.dao.tab.HomeBannerTabHandler;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeBannerRepository extends MybatisplusPageRepository<HomeBannerQueryDTO, HomeBannerMapper, HomeBannerModel> implements GenericPageRepository<HomeBannerQueryDTO, HomeBannerModel> {

    @Resource
    private List<HomeBannerTabHandler> tabHandlers;

    @Override
    public List<HomeBannerTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<HomeBannerModel> fillQueryWrapper(HomeBannerQueryDTO dto, IQueryWrapper<HomeBannerModel> wrapper) {
        return wrapper;
    }

}
