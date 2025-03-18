package com.beicroon.project.ronmio.dao.manager;

import com.beicroon.common.ronmio.enums.TotalAmountCalculationEnums;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.project.ronmio.dao.convertor.RonmioPackingListInvoiceConvertor;
import com.beicroon.project.ronmio.dao.helper.RonmioPackingListInvoiceHelper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceContainerModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import com.beicroon.project.ronmio.dao.repository.RonmioPackingListInvoiceContainerRepository;
import com.beicroon.project.ronmio.dao.repository.RonmioPackingListInvoiceRecordRepository;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordDetailVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class RonmioPackingListInvoiceManager {

    @Value("${ronmio.packing-list-invoice.code-prefix:PLI}")
    private String codePrefix;

    @Resource
    private RonmioPackingListInvoiceConvertor ronmioPackingListInvoiceConvertor;

    @Resource
    private RonmioPackingListInvoiceContainerRepository ronmioPackingListInvoiceContainerRepository;

    @Resource
    private RonmioPackingListInvoiceRecordRepository ronmioPackingListInvoiceRecordRepository;

    public String genericCode() {
        return RonmioPackingListInvoiceHelper.genericCode(this.codePrefix);
    }

    public RonmioPackingListInvoiceDetailVO getDetailVO(RonmioPackingListInvoiceModel model) {
        RonmioPackingListInvoiceDetailVO vo = this.ronmioPackingListInvoiceConvertor.toDetailVO(model);

        List<RonmioPackingListInvoiceContainerModel> containers = this.ronmioPackingListInvoiceContainerRepository.list(
                RonmioPackingListInvoiceContainerModel::getPackingListInvoiceId, model.getId()
        );

        List<RonmioPackingListInvoiceRecordModel> records = this.ronmioPackingListInvoiceRecordRepository.list(
                RonmioPackingListInvoiceRecordModel::getPackingListInvoiceId, model.getId()
        );

        Map<Long, List<RonmioPackingListInvoiceRecordModel>> recordMap = ListUtils.toMapList(
                records, RonmioPackingListInvoiceRecordModel::getContainerId
        );

        List<RonmioPackingListInvoiceContainerDetailVO> containerVOs = ListUtils.toList(containers, container -> {
            RonmioPackingListInvoiceContainerDetailVO containerVO = this.ronmioPackingListInvoiceConvertor.toContainerDetailVO(container);

            List<RonmioPackingListInvoiceRecordModel> containerRecords = recordMap.get(container.getId());

            Map<String, List<RonmioPackingListInvoiceRecordModel>> containerRecordMap = ListUtils.toMapList(
                    containerRecords, RonmioPackingListInvoiceHelper::getRecordKey
            );

            List<RonmioPackingListInvoiceRecordDetailVO> containerRecordVOs = ListUtils.toList(containerRecordMap.values(), groupRecords -> {
                RonmioPackingListInvoiceRecordDetailVO recordVO = this.ronmioPackingListInvoiceConvertor.toRecordDetailVO(
                        ListUtils.first(groupRecords)
                );

                Set<Integer> lengthSet = new HashSet<>(groupRecords.size());

                for (RonmioPackingListInvoiceRecordModel groupRecord : groupRecords) {
                    lengthSet.add(groupRecord.getLength());

                    recordVO.setCount(NumberUtils.add(recordVO.getCount(), groupRecord.getCount()));
                    recordVO.setVolume(NumberUtils.add(recordVO.getVolume(), groupRecord.getVolume()));
                    recordVO.setNetWeight(NumberUtils.add(recordVO.getNetWeight(), groupRecord.getNetWeight()));
                    recordVO.setGrossWeight(NumberUtils.add(recordVO.getGrossWeight(), groupRecord.getGrossWeight()));
                }

                List<Integer> lengthList = ListUtils.sort(lengthSet, Comparator.naturalOrder());

                if (lengthList.size() > 1) {
                    recordVO.setSpecification(String.format(
                            "%s*%s*(%s-%s)",
                            recordVO.getThickness(),
                            recordVO.getWidth(),
                            ListUtils.first(lengthList),
                            ListUtils.last(lengthList))
                    );
                } else {
                    recordVO.setSpecification(String.format("%s*%s*%s",
                            recordVO.getThickness(),
                            recordVO.getWidth(),
                            ListUtils.first(lengthList)
                    ));
                }

                // 单价乘以数量
                if (TotalAmountCalculationEnums.UNIT_PRICE_COUNT.getCode().equals(model.getTotalAmountCalculation())) {
                    recordVO.setTotalAmount(NumberUtils.multiply(recordVO.getUnitPrice(), recordVO.getCount()));
                }
                // 单价乘以体积
                else if (TotalAmountCalculationEnums.UNIT_PRICE_VOLUME.getCode().equals(model.getTotalAmountCalculation())) {
                    recordVO.setTotalAmount(NumberUtils.multiply(recordVO.getUnitPrice(), recordVO.getVolume()));
                }

                containerVO.setTotalAmount(NumberUtils.add(containerVO.getTotalAmount(), recordVO.getTotalAmount()));

                return recordVO;
            });

            Comparator<RonmioPackingListInvoiceRecordDetailVO> comparator = Comparator.comparing(RonmioPackingListInvoiceRecordDetailVO::getLevel)
                    .thenComparing(RonmioPackingListInvoiceRecordDetailVO::getSpecification);

            containerVO.setRecords(ListUtils.sort(containerRecordVOs, comparator));

            vo.setTotalAmount(NumberUtils.add(vo.getTotalAmount(), containerVO.getTotalAmount()));
            vo.setTotalVolume(NumberUtils.add(vo.getTotalVolume(), containerVO.getVolume()));
            vo.setTotalNetWeight(NumberUtils.add(vo.getTotalNetWeight(), containerVO.getNetWeight()));
            vo.setTotalGrossWeight(NumberUtils.add(vo.getTotalGrossWeight(), containerVO.getGrossWeight()));

            return containerVO;
        });

        vo.setContainers(ListUtils.sort(containerVOs, RonmioPackingListInvoiceContainerDetailVO::getCode));

        // 计算总量
        RonmioPackingListInvoiceHelper.calculateWeight(vo);

        return vo;
    }

}
