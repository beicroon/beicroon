import {BeicroonDisableVO, BeicroonQueryDTO, BeicroonRequestConfig} from "beicroon-app-vue";

export type QueryDTO = BeicroonQueryDTO & {
    code?: string;  // 箱单单号
    customerId?: string;  // 客户主键
    customerName?: string;  // 客户名称
    trainCode?: string;  // 班列号
    containerCode?: string;  // 货柜号
    sellerName?: string;  // 卖方名称
    sellerAddr?: string;  // 卖方地址
    buyerName?: string;  // 买方名称
    buyerAddr?: string;  // 买方地址
    contractCode?: string;  // 合同号
    originCountry?: string;  // 原产国
    priceTerm?: string;  // 价格条款
    boxType?: string;  // 箱型
    totalAmount?: string;  // 总金额
    totalVolume?: string;  // 总体积
    totalNetWeight?: string;  // 总净重
    totalGrossWeight?: string;  // 总毛重
    remark?: string;  // 备注
    totalAmountCalculation?: string;  // 总价计算方式
    netWeightFractional?: string;  // 净重小数位数
    grossWeightFractional?: string;  // 毛重小数位数
};

export type CreateDTO = {
    code?: string,  // 箱单单号
    customerId?: string,  // 客户主键
    customerName?: string,  // 客户名称
    trainCode?: string,  // 班列号
    containerCode?: string,  // 货柜号
    sellerName?: string,  // 卖方名称
    sellerAddr?: string,  // 卖方地址
    buyerName?: string,  // 买方名称
    buyerAddr?: string,  // 买方地址
    contractCode?: string,  // 合同号
    originCountry?: string,  // 原产国
    priceTerm?: string,  // 价格条款
    boxType?: string,  // 箱型
    totalAmount?: string,  // 总金额
    totalVolume?: string,  // 总体积
    totalNetWeight?: string,  // 总净重
    totalGrossWeight?: string,  // 总毛重
    remark?: string,  // 备注
    totalAmountCalculation?: string,  // 总价计算方式
    netWeightFractional?: string,  // 净重小数位数
    grossWeightFractional?: string,  // 毛重小数位数
};

export type UpdateDTO = {
    id?: string,  // 主键
    code?: string,  // 箱单单号
    customerId?: string,  // 客户主键
    customerName?: string,  // 客户名称
    trainCode?: string,  // 班列号
    containerCode?: string,  // 货柜号
    sellerName?: string,  // 卖方名称
    sellerAddr?: string,  // 卖方地址
    buyerName?: string,  // 买方名称
    buyerAddr?: string,  // 买方地址
    contractCode?: string,  // 合同号
    originCountry?: string,  // 原产国
    priceTerm?: string,  // 价格条款
    boxType?: string,  // 箱型
    totalAmount?: string,  // 总金额
    totalVolume?: string,  // 总体积
    totalNetWeight?: string,  // 总净重
    totalGrossWeight?: string,  // 总毛重
    remark?: string,  // 备注
    totalAmountCalculation?: string,  // 总价计算方式
    netWeightFractional?: string,  // 净重小数位数
    grossWeightFractional?: string,  // 毛重小数位数
};

export type BaseVO = BeicroonDisableVO & {
    code?: string,  // 箱单单号
    customerId?: string,  // 客户主键
    customerName?: string,  // 客户名称
    trainCode?: string,  // 班列号
    containerCode?: string,  // 货柜号
    sellerName?: string,  // 卖方名称
    sellerAddr?: string,  // 卖方地址
    buyerName?: string,  // 买方名称
    buyerAddr?: string,  // 买方地址
    contractCode?: string,  // 合同号
    originCountry?: string,  // 原产国
    priceTerm?: string,  // 价格条款
    boxType?: string,  // 箱型
    remark?: string,  // 备注
    totalAmountCalculation?: string,  // 总价计算方式
    netWeightFractional?: string,  // 净重小数位数
    grossWeightFractional?: string,  // 毛重小数位数
};

export type PageVO = BaseVO & {

};

export type DetailVO = BaseVO & {
    totalAmount?: string;  // 总金额
    totalVolume?: string;  // 总体积
    totalNetWeight?: string;  // 总净重
    totalGrossWeight?: string;  // 总毛重
    containers?: ContainerDetailVO[];  // 箱单列表
};

export type ContainerCreateDTO = {
    id?: string,  // 主键
    packingListInvoiceId?: string,  // 箱号发票主键
    code?: string,  // 箱号
    netWeight?: number,  // 净重(kg)
    grossWeight?: number,  // 毛重(kg)
    records: RecordDTO[],  // 码单列表
};

export type ContainerBaseVO = {
    id?: string,  // 主键
    packingListInvoiceId?: string,  // 箱单发票主键
    packingListInvoiceCode?: string,  // 箱单发票单号
    code?: string,  // 箱号
    count?: number,  // 件数
    volume?: number,  // 体积(m³)
    netWeight?: number,  // 净重(kg)
    grossWeight?: number,  // 毛重(kg)
};

export type ContainerSearchVO = ContainerBaseVO & {
    records: RecordDetailVO[],  // 码单列表
};

export type ContainerDetailVO = ContainerBaseVO & {
    totalAmount?: number,  // 总金额(元)
    records: RecordDetailVO[],  // 码单列表
};

export type RecordDTO = {
    level?: string,  // 等级
    thickness?: number,  // 厚度(mm)
    width?: number,  // 宽度(mm)
    length?: number,  // 长度(mm)
    count?: number,  // 件数
    volume?: number,  // 体积(m³)
    netWeight?: number,  // 净重(kg)
    grossWeight?: number,  // 毛重(kg)
    unitPrice?: number,  // 单价(元)
};

export type RecordVO = {
    packingListInvoiceId?: string,  // 箱单发票主键
    packingListInvoiceCode?: string,  // 箱单发票单号
    containerId?: string,  // 箱单主键
    containerCode?: string,  // 箱号
    receiveName?: string,  // 收货人
    level?: string,  // 等级
    thickness?: number,  // 厚度(mm)
    width?: number,  // 宽度(mm)
    length?: number,  // 长度(mm)
    commodityName?: string,  // 商品名称
    commodityCode?: string,  // 商品编码
    count?: number,  // 件数
    volume?: number,  // 体积(m³)
    netWeight?: number,  // 净重(kg)
    grossWeight?: number,  // 毛重(kg)
    unitPrice?: number,  // 单价(元)
    totalAmount?: number,  // 总金额(元)
    destination?: string,  // 目的地
    remark?: string,  // 备注
};

export type RecordDetailVO = RecordVO & {
    specification?: string,  // 规格
};

export type RecordGroupDTO = {
    level?: string,  // 等级
    thickness?: number,  // 厚度(mm)
    width?: number,  // 宽度(mm)
    minLength?: string,  // 最小长度(mm)
    maxLength?: string,  // 最大长度(mm)
    unitPrice?: number,  // 单价(元)
};

export type RecordGroupVO = {
    thickness?: number,  // 厚度(mm)
    width?: number,  // 宽度(mm)
    minLength?: string,  // 最小长度(mm)
    maxLength?: string,  // 最大长度(mm)
    level?: string,  // 等级
    unitPrice?: number,  // 单价(元)
};

const config= {
    tab: {
        url: "api/admin/ronmio-packing-list-invoice-tab",
        method: "POST",
    } as BeicroonRequestConfig,
    list: {
        url: "api/admin/ronmio-packing-list-invoice-list",
        method: "POST",
    } as BeicroonRequestConfig,
    page: {
        url: "api/admin/ronmio-packing-list-invoice-page",
        method: "POST",
    } as BeicroonRequestConfig,
    create: {
        url: "api/admin/ronmio-packing-list-invoice-create",
        method: "POST",
    } as BeicroonRequestConfig,
    update: {
        url: "api/admin/ronmio-packing-list-invoice-update",
        method: "PUT",
    } as BeicroonRequestConfig,
    show: {
        url: "api/admin/ronmio-packing-list-invoice-show",
        method: "GET",
    } as BeicroonRequestConfig,
    detail: {
        url: "api/admin/ronmio-packing-list-invoice-detail",
        method: "GET",
    } as BeicroonRequestConfig,
    remove: {
        url: "api/admin/ronmio-packing-list-invoice-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
    download: {
        url: "api/admin/ronmio-packing-list-invoice-download",
        method: "GET",
    } as BeicroonRequestConfig,
    containerAppend: {
        url: "api/admin/ronmio-packing-list-invoice-container-append",
        method: "POST",
    } as BeicroonRequestConfig,
    containerSearch: {
        url: "api/admin/ronmio-packing-list-invoice-container-search",
        method: "POST",
    } as BeicroonRequestConfig,
    containerRemove: {
        url: "api/admin/ronmio-packing-list-invoice-container-remove",
        method: "DELETE",
    } as BeicroonRequestConfig,
    recordGroupList: {
        url: "api/admin/ronmio-packing-list-invoice-record-group-list",
        method: "GET",
    } as BeicroonRequestConfig,
    fillUnitPrice: {
        url: "api/admin/ronmio-packing-list-invoice-fill-unit-price",
        method: "PUT",
    } as BeicroonRequestConfig,
};

export default config;
