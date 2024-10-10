package com.beicroon.service.address.web.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.Result;
import com.beicroon.construct.entity.WebResult;
import com.beicroon.service.address.entity.map.result.dto.MapResultCreateDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultQueryDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultUpdateDTO;
import com.beicroon.service.address.entity.map.result.vo.MapResultBaseVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultDetailVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultPageVO;
import com.beicroon.service.address.web.service.IMapResultService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "图商解析结果相关")
@RequestMapping(path = "/api/address/admin")
public class AdminMapResultController {

    @Resource
    private IMapResultService mapResultService;

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/map-result-show")
    public Result<MapResultBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.mapResultService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/map-result-detail")
    public Result<MapResultDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.mapResultService.detail(id));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/map-result-page")
    public Result<List<MapResultPageVO>> page(@Valid @RequestBody MapResultQueryDTO dto) {
        return WebResult.success(this.mapResultService.page(dto));
    }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/map-result-list")
    public Result<List<MapResultBaseVO>> list(@Valid @RequestBody MapResultQueryDTO dto) {
        return WebResult.success(this.mapResultService.list(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/map-result-create")
    public Result<Boolean> create(@Valid @RequestBody MapResultCreateDTO dto) {
        return WebResult.success(this.mapResultService.create(dto));
    }

    @ApiOperation(name = "单个修改")
    @PutMapping(path = "/map-result-update")
    public Result<Boolean> update(@Valid @RequestBody MapResultUpdateDTO dto) {
        return WebResult.success(this.mapResultService.update(dto));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/map-result-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.mapResultService.remove(dto));
    }

}
