package com.beicroon.project.ronmio.app.controller.admin;

import com.beicroon.construct.annotation.ApiOperation;
import com.beicroon.construct.annotation.ApiTag;
import com.beicroon.construct.base.entity.*;
import com.beicroon.project.ronmio.app.service.IRonmioPackingListInvoiceService;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceCreateDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceQueryDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceUpdateDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceBaseVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoicePageVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerAppendDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerSearchDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerSearchVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoiceprice.vo.RonmioPackingListInvoicePriceBaseVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.dto.RonmioPackingListInvoiceRecordFillUnitPriceDTO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@ApiTag(name = "箱单发票相关")
@RequestMapping(path = "/api/admin/admin")
public class AdminRonmioPackingListInvoiceController {

    @Resource
    private IRonmioPackingListInvoiceService ronmioPackingListInvoiceService;

    @ApiOperation(name = "分页统计")
    @PostMapping(path = "/ronmio-packing-list-invoice-tab")
    public Result<List<TabVO>> tab(@Valid @RequestBody RonmioPackingListInvoiceQueryDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.tab(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/ronmio-packing-list-invoice-list")
    public Result<List<RonmioPackingListInvoiceBaseVO>> list(@Valid @RequestBody RonmioPackingListInvoiceQueryDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.list(dto));
    }

    @ApiOperation(name = "分页列表")
    @PostMapping(path = "/ronmio-packing-list-invoice-page")
    public Result<List<RonmioPackingListInvoicePageVO>> page(@Valid @RequestBody RonmioPackingListInvoiceQueryDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.page(dto));
    }

    @ApiOperation(name = "单个新增")
    @PostMapping(path = "/ronmio-packing-list-invoice-create")
    public Result<Boolean> create(@Valid @RequestBody RonmioPackingListInvoiceCreateDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.create(dto));
    }

    @ApiOperation(name = "单个编辑")
    @PutMapping(path = "/ronmio-packing-list-invoice-update")
    public Result<Boolean> update(@Valid @RequestBody RonmioPackingListInvoiceUpdateDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.update(dto));
    }

    @ApiOperation(name = "基础信息")
    @GetMapping(path = "/ronmio-packing-list-invoice-show")
    public Result<RonmioPackingListInvoiceBaseVO> show(@RequestParam("id") Long id) {
        return WebResult.success(this.ronmioPackingListInvoiceService.show(id));
    }

    @ApiOperation(name = "详细信息")
    @GetMapping(path = "/ronmio-packing-list-invoice-detail")
    public Result<RonmioPackingListInvoiceDetailVO> detail(@RequestParam("id") Long id) {
        return WebResult.success(this.ronmioPackingListInvoiceService.detail(id));
    }

    @ApiOperation(name = "批量删除")
    @DeleteMapping(path = "/ronmio-packing-list-invoice-remove")
    public Result<Boolean> remove(@Valid @RequestBody IdsDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.remove(dto));
    }

    @ApiOperation(name = "下载excel")
    @GetMapping(path = "/ronmio-packing-list-invoice-download")
    public Result<String> download(@RequestParam("id") Long id) {
        return WebResult.success(this.ronmioPackingListInvoiceService.download(id));
    }

    @ApiOperation(name = "添加箱单")
    @PostMapping(path = "/ronmio-packing-list-invoice-container-append")
    public Result<Boolean> containerAppend(@Valid @RequestBody RonmioPackingListInvoiceContainerAppendDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.containerAppend(dto));
    }

    @ApiOperation(name = "搜索箱单")
    @PostMapping(path = "/ronmio-packing-list-invoice-container-search")
    public Result<RonmioPackingListInvoiceContainerSearchVO> containerSearch(@Valid @RequestBody RonmioPackingListInvoiceContainerSearchDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.containerSearch(dto));
    }

    @ApiOperation(name = "删除箱单")
    @DeleteMapping(path = "/ronmio-packing-list-invoice-container-remove")
    public Result<Boolean> containerRemove(@Valid @RequestBody IdDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.containerRemove(dto));
    }

    @ApiOperation(name = "码单分组列表")
    @GetMapping(path = "/ronmio-packing-list-invoice-record-group-list")
    public Result<List<RonmioPackingListInvoicePriceBaseVO>> recordGroupList(@RequestParam("packingListInvoiceId") Long packingListInvoiceId) {
        return WebResult.success(this.ronmioPackingListInvoiceService.recordGroupList(packingListInvoiceId));
    }

    @ApiOperation(name = "填写单价")
    @PutMapping(path = "/ronmio-packing-list-invoice-fill-unit-price")
    public Result<Boolean> fillUnitPrice(@Valid @RequestBody RonmioPackingListInvoiceRecordFillUnitPriceDTO dto) {
        return WebResult.success(this.ronmioPackingListInvoiceService.fillUnitPrice(dto));
    }

}
