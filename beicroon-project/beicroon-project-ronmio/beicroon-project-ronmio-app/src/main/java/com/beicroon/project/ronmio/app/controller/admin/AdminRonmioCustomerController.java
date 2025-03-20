package com.beicroon.project.ronmio.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.project.ronmio.app.service.IRonmioCustomerService;
import com.beicroon.project.ronmio.entity.ronmiocustomer.dto.RonmioCustomerCreateDTO;
import com.beicroon.project.ronmio.entity.ronmiocustomer.dto.RonmioCustomerQueryDTO;
import com.beicroon.project.ronmio.entity.ronmiocustomer.dto.RonmioCustomerUpdateDTO;
import com.beicroon.project.ronmio.entity.ronmiocustomer.vo.RonmioCustomerBaseVO;
import com.beicroon.project.ronmio.entity.ronmiocustomer.vo.RonmioCustomerDetailVO;
import com.beicroon.project.ronmio.entity.ronmiocustomer.vo.RonmioCustomerPageVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "客户相关")
@RequestMapping(path = "/api/admin")
public class AdminRonmioCustomerController {

    @Resource
    private IRonmioCustomerService ronmioCustomerService;

   @ApiOperation(name = "分页统计")
   @PostMapping(path = "/ronmio-customer-tab")
   public Result<List<TabVO>> tab(@Valid @RequestBody RonmioCustomerQueryDTO dto) {
       return WebResult.success(this.ronmioCustomerService.tab(dto));
   }

    @ApiOperation(name = "全量列表")
    @PostMapping(path = "/ronmio-customer-list")
    public Result<List<RonmioCustomerBaseVO>> list(@Valid @RequestBody RonmioCustomerQueryDTO dto) {
        return WebResult.success(this.ronmioCustomerService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/ronmio-customer-page")
    public Result<List<RonmioCustomerPageVO>> page(@Valid @RequestBody RonmioCustomerQueryDTO dto) {
        return WebResult.success(this.ronmioCustomerService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/ronmio-customer-create")
    public Result<Boolean> create(@Valid @RequestBody RonmioCustomerCreateDTO dto) {
        return WebResult.success(this.ronmioCustomerService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/ronmio-customer-update")
    public Result<Boolean> update(@Valid @RequestBody RonmioCustomerUpdateDTO dto) {
        return WebResult.success(this.ronmioCustomerService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/ronmio-customer-show")
    public Result<RonmioCustomerBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.ronmioCustomerService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/ronmio-customer-detail")
    public Result<RonmioCustomerDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.ronmioCustomerService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/ronmio-customer-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.ronmioCustomerService.remove(dto));
    }

}
