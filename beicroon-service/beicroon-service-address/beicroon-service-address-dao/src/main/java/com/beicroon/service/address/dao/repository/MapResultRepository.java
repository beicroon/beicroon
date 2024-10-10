package com.beicroon.service.address.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.address.dao.mapper.MapResultMapper;
import com.beicroon.service.address.dao.model.MapResultModel;
import com.beicroon.service.address.entity.map.result.dto.MapResultQueryDTO;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MapResultRepository extends GenericRepository<MapResultQueryDTO, MapResultMapper, MapResultModel> implements IGenericRepository<MapResultQueryDTO, MapResultModel> {

    @Override
    public void fillQueryWrapper(MapResultQueryDTO dto, QueryWrapper<MapResultModel> wrapper) {
        LambdaQueryWrapper<MapResultModel> query = wrapper.lambda();
    }

}
