package com.beicroon.service.ronmio.app.service.impl;

import com.beicroon.construct.base.entity.IdDTO;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.construct.database.wrapper.IUpdateWrapper;
import com.beicroon.construct.excel.data.DataExcel;
import com.beicroon.construct.excel.data.DataRow;
import com.beicroon.construct.excel.enums.HorizontalAlignmentEnums;
import com.beicroon.construct.excel.enums.VerticalAlignmentEnums;
import com.beicroon.construct.excel.writer.IExcelWriter;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.service.ronmio.app.excel.RonmioPackingListInvoiceExcel;
import com.beicroon.service.ronmio.app.service.IRonmioPackingListInvoiceService;
import com.beicroon.service.ronmio.dao.convertor.RonmioPackingListInvoiceConvertor;
import com.beicroon.service.ronmio.dao.helper.RonmioPackingListInvoiceHelper;
import com.beicroon.service.ronmio.dao.manager.RonmioPackingListInvoiceManager;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceContainerModel;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoicePriceModel;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import com.beicroon.service.ronmio.dao.repository.RonmioPackingListInvoiceContainerRepository;
import com.beicroon.service.ronmio.dao.repository.RonmioPackingListInvoicePriceRepository;
import com.beicroon.service.ronmio.dao.repository.RonmioPackingListInvoiceRecordRepository;
import com.beicroon.service.ronmio.dao.repository.RonmioPackingListInvoiceRepository;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceCreateDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceQueryDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceUpdateDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceBaseVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoicePageVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerAppendDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerSearchDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerDetailVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerSearchVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoiceprice.dto.RonmioPackingListInvoicePriceUpgradeDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoiceprice.vo.RonmioPackingListInvoicePriceBaseVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicerecord.dto.RonmioPackingListInvoiceRecordFillUnitPriceDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordDetailVO;
import com.beicroon.starter.database.manager.DBManager;
import com.beicroon.starter.excel.utils.ExcelUtils;
import com.beicroon.starter.oss.manager.OssManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RonmioPackingListInvoiceService implements IRonmioPackingListInvoiceService {

    @Resource
    private DBManager dbManager;

    @Resource
    private OssManager ossManager;

    @Resource
    private RonmioPackingListInvoiceManager ronmioPackingListInvoiceManager;

    @Resource
    private RonmioPackingListInvoiceConvertor ronmioPackingListInvoiceConvertor;

    @Resource
    private RonmioPackingListInvoiceRepository ronmioPackingListInvoiceRepository;

    @Resource
    private RonmioPackingListInvoiceContainerRepository ronmioPackingListInvoiceContainerRepository;

    @Resource
    private RonmioPackingListInvoiceRecordRepository ronmioPackingListInvoiceRecordRepository;

    @Resource
    private RonmioPackingListInvoicePriceRepository ronmioPackingListInvoicePriceRepository;

    @Override
    public List<TabVO> tab(RonmioPackingListInvoiceQueryDTO dto) {
        return this.ronmioPackingListInvoiceRepository.tab(dto);
    }

    @Override
    public List<RonmioPackingListInvoiceBaseVO> list(RonmioPackingListInvoiceQueryDTO dto) {
        List<RonmioPackingListInvoiceModel> list = this.ronmioPackingListInvoiceRepository.list(dto);

        return this.ronmioPackingListInvoiceConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<RonmioPackingListInvoicePageVO> page(RonmioPackingListInvoiceQueryDTO dto) {
        PageInfo<RonmioPackingListInvoiceModel> page = this.ronmioPackingListInvoiceRepository.page(dto);

        return this.ronmioPackingListInvoiceConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(RonmioPackingListInvoiceCreateDTO dto) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceConvertor.toCreator(dto);

        model.setCode(this.ronmioPackingListInvoiceManager.genericCode());

        return this.ronmioPackingListInvoiceRepository.insert(model);
    }

    @Override
    public Boolean update(RonmioPackingListInvoiceUpdateDTO dto) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceConvertor.toUpdater(dto);

        return this.ronmioPackingListInvoiceRepository.updateById(model);
    }

    @Override
    public RonmioPackingListInvoiceBaseVO show(Long id) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceRepository.getById(id);

        return this.ronmioPackingListInvoiceConvertor.toBaseVO(model);
    }

    @Override
    public RonmioPackingListInvoiceDetailVO detail(Long id) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceRepository.getById(id);

        if (model == null) {
            return null;
        }

        return this.ronmioPackingListInvoiceManager.getDetailVO(model);
    }

    @Override
    public String download(Long id) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceRepository.getByIdOrError(id, "箱单发票不存在或已删除");

        RonmioPackingListInvoiceDetailVO vo = this.ronmioPackingListInvoiceManager.getDetailVO(model);

        DataExcel excel = DataExcel.of(9, RonmioPackingListInvoiceExcel.class, (short) 36);

        excel.addRow("Packing list and Invoice", excel.getCellSize(), HorizontalAlignmentEnums.CENTER, VerticalAlignmentEnums.CENTER);
        excel.addRow(String.format("SELLER: %s", model.getSellerName()), excel.getCellSize());
        excel.addRow(String.format("ADD: %s", model.getSellerAddr()), excel.getCellSize());
        excel.addRow(String.format("BUYER: %s", model.getBuyerName()), excel.getCellSize());
        excel.addRow(String.format("ADD: %s", model.getBuyerAddr()), excel.getCellSize());
        excel.addRow(String.format("合同号: %s", model.getContractCode()), excel.getCellSize());
        excel.addRow(String.format("Country of origin: %s", model.getOriginCountry()), excel.getCellSize());
        excel.addRow(String.format("Price term: %s", model.getPriceTerm()), excel.getCellSize());
        excel.addRow(String.format("箱型: %s", model.getBoxType()), excel.getCellSize());

        AtomicInteger index = new AtomicInteger(1);

        AtomicReference<BigDecimal> totalCount = new AtomicReference<>(EmptyUtils.emptyBigDecimal());
        AtomicReference<BigDecimal> totalVolume = new AtomicReference<>(EmptyUtils.emptyBigDecimal());

        for (RonmioPackingListInvoiceContainerDetailVO container : vo.getContainers()) {
            totalCount.set(NumberUtils.add(totalCount.get(), container.getCount()));
            totalVolume.set(NumberUtils.add(totalVolume.get(), container.getVolume()));

            List<RonmioPackingListInvoiceRecordDetailVO> records = container.getRecords();

            ListUtils.foreach(records, (RonmioPackingListInvoiceRecordDetailVO record, Integer i) -> {
                DataRow row = DataRow.of(HorizontalAlignmentEnums.CENTER, VerticalAlignmentEnums.CENTER);

                Integer rowspan = i == 0 ? records.size() : null;

                boolean condition = rowspan != null;

                if (condition) {
                    row.addCell(index.getAndIncrement(), rowspan);  // 序号
                } else {
                    row.addCell(null);  // 序号
                }

                row.addCell(EmptyUtils.getValueOrNull(condition, container.getCode()), rowspan);  // 箱号
                row.addCell(record.getReceiveName());  // 收货人
                row.addCell(record.getLevel());  // 等级
                row.addCell(record.getSpecification());  // 规格
                row.addCell(record.getCommodityName());  // 商品名称
                row.addCell(record.getCommodityCode());  // 商品编码
                row.addCell(EmptyUtils.getValueOrNull(record.getCount()));  // 商品件数
                row.addCell(EmptyUtils.getValueOrNull(condition, container.getCount()), rowspan);  // 商品总件数
                row.addCell(EmptyUtils.getValueOrNull(record.getVolume()));  // 商品体积
                row.addCell(EmptyUtils.getValueOrNull(condition, container.getVolume()), rowspan);  // 商品总体积
                row.addCell(EmptyUtils.getValueOrNull(record.getNetWeight()));  // 净重
                row.addCell(EmptyUtils.getValueOrNull(condition, container.getNetWeight()), rowspan);  // 总净重
                row.addCell(EmptyUtils.getValueOrNull(record.getGrossWeight()));  // 毛重
                row.addCell(EmptyUtils.getValueOrNull(condition, container.getGrossWeight()), rowspan);  // 总毛重
                row.addCell(EmptyUtils.getValueOrNull(record.getUnitPrice()));  // 单价
                row.addCell(EmptyUtils.getValueOrNull(record.getTotalAmount()));  // 总价
                row.addCell(EmptyUtils.getValueOrNull(condition, container.getTotalAmount()), rowspan);  // 总货值
                row.addCell(record.getDestination());  // 目的地
                row.addCell(record.getRemark());  // 备注

                excel.addRow(row);
            });
        }

        excel.addRow(DataRow.of(
                "总计",  // 序号
                null,  // 箱号
                null,  // 收货人
                null,  // 等级
                null,  // 规格
                null,  // 商品名称
                null,  // 商品编码
                null,  // 商品件数
                String.format("总件数：%s", totalCount.get()),  // 商品总件数
                null,  // 商品体积
                String.format("总体积：%s", totalVolume.get()),  // 商品总体积
                null,  // 净重
                String.format("总净重：%s", vo.getTotalNetWeight()),  // 总净重
                null,  // 毛重
                String.format("总毛重：%s", vo.getTotalGrossWeight()),  // 总毛重
                null,  // 单价
                null,  // 总价
                String.format("总货值：%s", vo.getTotalAmount()),  // 总货值
                null,  // 目的地
                null  // 备注
        ));

        IExcelWriter<?> writer = ExcelUtils.getWriter();

        writer.write(excel, "sheet1");

        return this.ossManager.upload(writer.getBytes(), RonmioPackingListInvoiceHelper.getExportName(model), "xlsx");
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.ronmioPackingListInvoiceRepository.removeByIds(dto.getIds());
    }

    @Override
    public Boolean containerAppend(RonmioPackingListInvoiceContainerAppendDTO dto) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceRepository.getByIdOrError(
                dto.getPackingListInvoiceId(), "箱单发票不存在或已删除"
        );

        RonmioPackingListInvoiceContainerModel container = this.ronmioPackingListInvoiceConvertor.toContainerCreator(model, dto);

        List<RonmioPackingListInvoiceRecordModel> records = this.ronmioPackingListInvoiceConvertor.toRecordCreator(model, container, dto.getRecords());

        return this.dbManager.execute(() -> {
            if (EmptyUtils.isId(dto.getId())) {
                this.ronmioPackingListInvoiceContainerRepository.deleteById(dto.getId());

                this.ronmioPackingListInvoiceRecordRepository.delete(
                        RonmioPackingListInvoiceRecordModel::getContainerId, dto.getId()
                );
            }

            this.ronmioPackingListInvoiceContainerRepository.insert(container);

            this.ronmioPackingListInvoiceRecordRepository.insert(records);

            this.ronmioPackingListInvoicePriceRepository.delete(
                    RonmioPackingListInvoicePriceModel::getPackingListInvoiceId, model.getId()
            );
        });
    }

    @Override
    public RonmioPackingListInvoiceContainerSearchVO containerSearch(RonmioPackingListInvoiceContainerSearchDTO dto) {
        IQueryWrapper<RonmioPackingListInvoiceContainerModel> query = this.ronmioPackingListInvoiceContainerRepository.newQueryWrapper();

        query.eq(RonmioPackingListInvoiceContainerModel::getPackingListInvoiceId, dto.getPackingListInvoiceId());
        query.eq(RonmioPackingListInvoiceContainerModel::getCode, dto.getCode());

        RonmioPackingListInvoiceContainerModel container = this.ronmioPackingListInvoiceContainerRepository.first(query);

        if (container == null) {
            return null;
        }

        List<RonmioPackingListInvoiceRecordModel> records = this.ronmioPackingListInvoiceRecordRepository.list(
                RonmioPackingListInvoiceRecordModel::getContainerId, container.getId()
        );

        RonmioPackingListInvoiceContainerSearchVO vo = this.ronmioPackingListInvoiceConvertor.toContainerSearchVO(container);

        vo.setRecords(this.ronmioPackingListInvoiceConvertor.toRecordBaseVO(records));

        return vo;
    }

    @Override
    public Boolean containerRemove(IdDTO dto) {
        RonmioPackingListInvoiceContainerModel model = this.ronmioPackingListInvoiceContainerRepository.getByIdOrError(
                dto.getId(), "箱单不存在或已删除"
        );

        return this.dbManager.execute(() -> {
            this.ronmioPackingListInvoiceContainerRepository.deleteById(model.getId());

            this.ronmioPackingListInvoiceRecordRepository.delete(
                    RonmioPackingListInvoiceRecordModel::getContainerId, model.getId()
            );

            this.ronmioPackingListInvoicePriceRepository.delete(
                    RonmioPackingListInvoicePriceModel::getPackingListInvoiceId, model.getPackingListInvoiceId()
            );
        });
    }

    @Override
    public List<RonmioPackingListInvoicePriceBaseVO> recordGroupList(Long packingListInvoiceId) {
        List<RonmioPackingListInvoicePriceModel> models = this.ronmioPackingListInvoicePriceRepository.list(
                RonmioPackingListInvoicePriceModel::getPackingListInvoiceId, packingListInvoiceId
        );

        if (EmptyUtils.isNotEmpty(models)) {
            return this.ronmioPackingListInvoiceConvertor.toPriceBaseVO(models);
        }

        List<RonmioPackingListInvoiceRecordModel> records = this.ronmioPackingListInvoiceRecordRepository.list(
                RonmioPackingListInvoiceRecordModel::getPackingListInvoiceId, packingListInvoiceId
        );

        Map<String, List<RonmioPackingListInvoiceRecordModel>> recordMap = ListUtils.toMapList(
                records, RonmioPackingListInvoiceHelper::getRecordKey
        );

        List<RonmioPackingListInvoicePriceBaseVO> groups = ListUtils.toList(recordMap, group -> {
            Set<Integer> lengthSet = new HashSet<>(group.size());

            AtomicReference<RonmioPackingListInvoicePriceBaseVO> groupVOAto = new AtomicReference<>();

            for (RonmioPackingListInvoiceRecordModel model : group) {
                lengthSet.add(model.getLength());

                if (groupVOAto.get() == null) {
                    groupVOAto.set(this.ronmioPackingListInvoiceConvertor.toPriceBaseVO(model));
                }
            }

            RonmioPackingListInvoicePriceBaseVO groupVO = groupVOAto.get();

            List<Integer> lengthList = ListUtils.sort(lengthSet, Comparator.naturalOrder());
            
            groupVO.setMinLength(ListUtils.first(lengthList));

            if (lengthList.size() > 1) {
                groupVO.setMaxLength(ListUtils.last(lengthList));
            }

            return groupVO;
        });

        Comparator<RonmioPackingListInvoicePriceBaseVO> comparator = Comparator.comparing(RonmioPackingListInvoicePriceBaseVO::getThickness)
                .thenComparing(RonmioPackingListInvoicePriceBaseVO::getWidth)
                .thenComparing(RonmioPackingListInvoicePriceBaseVO::getLevel);

        return ListUtils.sort(groups, comparator);
    }

    @Override
    public Boolean fillUnitPrice(RonmioPackingListInvoiceRecordFillUnitPriceDTO dto) {
        RonmioPackingListInvoiceModel model = this.ronmioPackingListInvoiceRepository.getByIdOrError(
                dto.getPackingListInvoiceId(), "箱单发票不存在或已删除"
        );

        return this.dbManager.execute(() -> {
            List<RonmioPackingListInvoicePriceUpgradeDTO> groups = dto.getGroups();

            List<RonmioPackingListInvoicePriceModel> models = new ArrayList<>(groups.size());

            for (RonmioPackingListInvoicePriceUpgradeDTO group : groups) {
                models.add(this.ronmioPackingListInvoiceConvertor.toPriceModel(model, group));

                if (!EmptyUtils.isBigDecimal(group.getUnitPrice())) {
                    continue;
                }

                IQueryWrapper<RonmioPackingListInvoiceRecordModel> query = this.ronmioPackingListInvoiceRecordRepository.newQueryWrapper();

                query.eq(RonmioPackingListInvoiceRecordModel::getPackingListInvoiceId, dto.getPackingListInvoiceId());
                query.eq(RonmioPackingListInvoiceRecordModel::getLevel, group.getLevel());
                query.eq(RonmioPackingListInvoiceRecordModel::getThickness, group.getThickness());
                query.eq(RonmioPackingListInvoiceRecordModel::getWidth, group.getWidth());

                if (EmptyUtils.isInteger(group.getMaxLength())) {
                    query.between(RonmioPackingListInvoiceRecordModel::getLength, group.getMinLength(), group.getMaxLength());
                } else {
                    query.eq(RonmioPackingListInvoiceRecordModel::getLength, group.getMinLength());
                }

                Set<Long> ids = this.ronmioPackingListInvoiceRecordRepository.list(query, RonmioPackingListInvoiceRecordModel::getId);

                if (EmptyUtils.isEmpty(ids)) {
                    continue;
                }

                IUpdateWrapper<RonmioPackingListInvoiceRecordModel> updater = this.ronmioPackingListInvoiceRecordRepository.newUpdateWrapper();

                updater.in(RonmioPackingListInvoiceRecordModel::getId, ids);
                updater.set(RonmioPackingListInvoiceRecordModel::getUnitPrice, group.getUnitPrice());

                this.ronmioPackingListInvoiceRecordRepository.update(updater);
            }

            this.ronmioPackingListInvoicePriceRepository.delete(
                    RonmioPackingListInvoicePriceModel::getPackingListInvoiceId, model.getId()
            );

            this.ronmioPackingListInvoicePriceRepository.insert(models);
        });
    }

}
