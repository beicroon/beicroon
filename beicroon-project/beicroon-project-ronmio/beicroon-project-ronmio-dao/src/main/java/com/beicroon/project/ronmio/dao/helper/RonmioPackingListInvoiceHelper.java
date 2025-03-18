package com.beicroon.project.ronmio.dao.helper;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.construct.utils.SequenceUtils;
import com.beicroon.construct.utils.TimeUtils;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordDetailVO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicReference;

public final class RonmioPackingListInvoiceHelper {

    private RonmioPackingListInvoiceHelper() {

    }

    public static String genericCode(String codePrefix) {
        return SequenceUtils.getDatetimeCode(codePrefix);
    }

    public static String getRecordKey(RonmioPackingListInvoiceRecordModel model) {
        StringBuilder key = new StringBuilder();

        if (EmptyUtils.isNotEmpty(model.getLevel())) {
            key.append(model.getLevel());
        }

        if (EmptyUtils.isInteger(model.getThickness())) {
            key.append(model.getThickness());
        }

        if (EmptyUtils.isInteger(model.getWidth())) {
            key.append(model.getWidth());
        }

        if (EmptyUtils.isBigDecimal(model.getUnitPrice())) {
            key.append(model.getUnitPrice());
        }

        return key.toString();
    }

    public static String getExportName(RonmioPackingListInvoiceModel model) {
        return String.format(
                "%s-%s-%s-箱单发票-%s",
                model.getTrainCode(),
                model.getCustomerName(),
                model.getContainerCode(),
                TimeUtils.currentTimestamp()
        );
    }

    public static void calculateWeight(RonmioPackingListInvoiceDetailVO vo) {
        for (RonmioPackingListInvoiceContainerDetailVO container : vo.getContainers()) {
            if (EmptyUtils.isBigDecimal(container.getVolume())) {
                AtomicReference<RonmioPackingListInvoiceRecordDetailVO> last = new AtomicReference<>();

                AtomicReference<BigDecimal> totalNetWeight = new AtomicReference<>(BigDecimal.ZERO);
                AtomicReference<BigDecimal> totalGrossWeight = new AtomicReference<>(BigDecimal.ZERO);

                for (RonmioPackingListInvoiceRecordDetailVO record : container.getRecords()) {
                    last.set(record);

                    BigDecimal ratio = NumberUtils.divide(
                            NumberUtils.bigDecimalOf(record.getVolume(), 8),
                            NumberUtils.bigDecimalOf(container.getVolume(), 8),
                            RoundingMode.DOWN
                    );

                    record.setNetWeight(NumberUtils.getDecimalPlace(
                            NumberUtils.multiply(container.getNetWeight(), ratio), vo.getNetWeightFractional()
                    ));

                    record.setGrossWeight(NumberUtils.getDecimalPlace(
                            NumberUtils.multiply(container.getGrossWeight(), ratio), vo.getGrossWeightFractional()
                    ));

                    totalNetWeight.set(NumberUtils.add(totalNetWeight.get(), record.getNetWeight()));
                    totalGrossWeight.set(NumberUtils.add(totalGrossWeight.get(), record.getGrossWeight()));
                }

                RonmioPackingListInvoiceRecordDetailVO lastRecord = last.get();

                if (lastRecord != null) {
                    lastRecord.setNetWeight(NumberUtils.add(
                            lastRecord.getNetWeight(), NumberUtils.subtract(container.getNetWeight(), totalNetWeight.get())
                    ));

                    lastRecord.setGrossWeight(NumberUtils.add(
                            lastRecord.getGrossWeight(), NumberUtils.subtract(container.getGrossWeight(), totalGrossWeight.get())
                    ));
                }
            }
        }
    }

}
