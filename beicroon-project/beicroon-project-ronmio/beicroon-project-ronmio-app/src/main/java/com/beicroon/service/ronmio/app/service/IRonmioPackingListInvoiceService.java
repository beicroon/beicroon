package com.beicroon.service.ronmio.app.service;

import com.beicroon.construct.base.entity.IdDTO;
import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceCreateDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceQueryDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceUpdateDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceBaseVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoicePageVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerAppendDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerSearchDTO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerSearchVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoiceprice.vo.RonmioPackingListInvoicePriceBaseVO;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicerecord.dto.RonmioPackingListInvoiceRecordFillUnitPriceDTO;

import java.util.List;

public interface IRonmioPackingListInvoiceService {

    List<TabVO> tab(RonmioPackingListInvoiceQueryDTO dto);

    List<RonmioPackingListInvoiceBaseVO> list(RonmioPackingListInvoiceQueryDTO dto);

    PageInfo<RonmioPackingListInvoicePageVO> page(RonmioPackingListInvoiceQueryDTO dto);

    Boolean create(RonmioPackingListInvoiceCreateDTO dto);

    Boolean update(RonmioPackingListInvoiceUpdateDTO dto);

    RonmioPackingListInvoiceBaseVO show(Long id);

    RonmioPackingListInvoiceDetailVO detail(Long id);

    String download(Long id);

    Boolean remove(IdsDTO dto);

    Boolean containerAppend(RonmioPackingListInvoiceContainerAppendDTO dto);

    RonmioPackingListInvoiceContainerSearchVO containerSearch(RonmioPackingListInvoiceContainerSearchDTO dto);

    Boolean containerRemove(IdDTO dto);

    List<RonmioPackingListInvoicePriceBaseVO> recordGroupList(Long packingListInvoiceId);

    Boolean fillUnitPrice(RonmioPackingListInvoiceRecordFillUnitPriceDTO dto);

}
