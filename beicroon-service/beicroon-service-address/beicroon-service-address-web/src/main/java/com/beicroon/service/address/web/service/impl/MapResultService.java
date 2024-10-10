package com.beicroon.service.address.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.List;
import com.beicroon.service.address.dao.convertor.MapResultConvertor;
import com.beicroon.service.address.dao.model.MapResultModel;
import com.beicroon.service.address.dao.repository.MapResultRepository;
import com.beicroon.service.address.entity.map.result.dto.MapResultCreateDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultQueryDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultUpdateDTO;
import com.beicroon.service.address.entity.map.result.vo.MapResultBaseVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultDetailVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultPageVO;
import com.beicroon.service.address.web.service.IMapResultService;
import com.beicroon.starter.mysql.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MapResultService implements IMapResultService {

    @Resource
    private MapResultConvertor mapResultConvertor;

    @Resource
    private MapResultRepository mapResultRepository;

    @Override
    public MapResultBaseVO show(Long id) {
        MapResultModel mapResult = this.mapResultRepository.getById(id);

        return this.mapResultConvertor.toBaseVO(mapResult);
    }

    @Override
    public MapResultDetailVO detail(Long id) {
        MapResultModel mapResult = this.mapResultRepository.getById(id);
        
        return this.mapResultConvertor.toDetailVO(mapResult);
    }

    @Override
    public List<MapResultBaseVO> list(MapResultQueryDTO dto) {
        List<MapResultModel> list = this.mapResultRepository.list(dto);
        
        return List.toList(list, this.mapResultConvertor::toBaseVO);
    }

    @Override
    public PageInfo<MapResultPageVO> page(MapResultQueryDTO dto) {
        Page<MapResultModel> page = this.mapResultRepository.page(dto);
        
        return PageUtils.result(page, this.mapResultConvertor::toPageVO);
    }

    @Override
    public boolean create(MapResultCreateDTO dto) {
        MapResultModel creator = this.mapResultConvertor.toEntity(dto);
        
        return this.mapResultRepository.save(creator);
    }

    @Override
    public boolean update(MapResultUpdateDTO dto) {
        MapResultModel updater = this.mapResultConvertor.toEntity(dto);
        
        return this.mapResultRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.mapResultRepository.removeByIds(dto.getIds());
    }

}
