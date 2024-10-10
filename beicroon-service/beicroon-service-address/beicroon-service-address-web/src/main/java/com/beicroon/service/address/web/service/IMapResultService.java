package com.beicroon.service.address.web.service;

import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.service.address.entity.map.result.dto.MapResultCreateDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultQueryDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultUpdateDTO;
import com.beicroon.service.address.entity.map.result.vo.MapResultBaseVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultDetailVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultPageVO;

import java.util.List;

public interface IMapResultService {

    MapResultBaseVO show(Long id);

    MapResultDetailVO detail(Long id);

    List<MapResultBaseVO> list(MapResultQueryDTO dto);

    PageInfo<MapResultPageVO> page(MapResultQueryDTO dto);

    boolean create(MapResultCreateDTO dto);

    boolean update(MapResultUpdateDTO dto);

    boolean remove(IdsDTO dto);

}
