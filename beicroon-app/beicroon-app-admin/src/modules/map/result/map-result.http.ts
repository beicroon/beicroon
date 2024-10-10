import http, {
    BaseVO,
    DisableDTO,
    DisableVO,
    PageInfo,
    QueryDTO,
    Response,
    UpdateDTO
} from "@/utils/beicroon/http.utils.ts";

// 基础返回字段
export type MapResultBaseVO = BaseVO & DisableVO & {
    originAddress?: string,  // 原地址
    originAddressHash?: string,  // 原地址CRC32值
    gaodeProvince?: string,  // 高德省份
    gaodeCity?: string,  // 高德城市
    gaodeDistrict?: string,  // 高德区划
    gaodeStreet?: string,  // 高德街道
    gaodeAddress?: string,  // 高德地址
    gaodeAddressLng?: string,  // 高德地址经度
    gaodeAddressLat?: string,  // 高德地址纬度
    tengxunProvince?: string,  // 腾讯省份
    tengxunCity?: string,  // 腾讯城市
    tengxunDistrict?: string,  // 腾讯区划
    tengxunStreet?: string,  // 腾讯街道
    tengxunAddress?: string,  // 腾讯地址
    tengxunAddressLng?: string,  // 腾讯地址经度
    tengxunAddressLat?: string,  // 腾讯地址纬度
    baiduProvince?: string,  // 百度省份
    baiduCity?: string,  // 百度城市
    baiduDistrict?: string,  // 百度区划
    baiduStreet?: string,  // 百度街道
    baiduAddress?: string,  // 百度地址
    baiduAddressLng?: string,  // 百度地址经度
    baiduAddressLat?: string,  // 百度地址纬度
}

// 详情返回字段
export type MapResultDetailVO = MapResultBaseVO & {

}

// 分页返回字段
export type MapResultPageVO = MapResultBaseVO & {

}

// 新增参数字段
export type MapResultCreateDTO = DisableDTO & {
    originAddress?: string,  // 原地址
    originAddressHash?: string,  // 原地址CRC32值
    gaodeProvince?: string,  // 高德省份
    gaodeCity?: string,  // 高德城市
    gaodeDistrict?: string,  // 高德区划
    gaodeStreet?: string,  // 高德街道
    gaodeAddress?: string,  // 高德地址
    gaodeAddressLng?: string,  // 高德地址经度
    gaodeAddressLat?: string,  // 高德地址纬度
    tengxunProvince?: string,  // 腾讯省份
    tengxunCity?: string,  // 腾讯城市
    tengxunDistrict?: string,  // 腾讯区划
    tengxunStreet?: string,  // 腾讯街道
    tengxunAddress?: string,  // 腾讯地址
    tengxunAddressLng?: string,  // 腾讯地址经度
    tengxunAddressLat?: string,  // 腾讯地址纬度
    baiduProvince?: string,  // 百度省份
    baiduCity?: string,  // 百度城市
    baiduDistrict?: string,  // 百度区划
    baiduStreet?: string,  // 百度街道
    baiduAddress?: string,  // 百度地址
    baiduAddressLng?: string,  // 百度地址经度
    baiduAddressLat?: string,  // 百度地址纬度
}

// 修改参数字段
export type MapResultUpdateDTO = UpdateDTO & {
    originAddress?: string,  // 原地址
    originAddressHash?: string,  // 原地址CRC32值
    gaodeProvince?: string,  // 高德省份
    gaodeCity?: string,  // 高德城市
    gaodeDistrict?: string,  // 高德区划
    gaodeStreet?: string,  // 高德街道
    gaodeAddress?: string,  // 高德地址
    gaodeAddressLng?: string,  // 高德地址经度
    gaodeAddressLat?: string,  // 高德地址纬度
    tengxunProvince?: string,  // 腾讯省份
    tengxunCity?: string,  // 腾讯城市
    tengxunDistrict?: string,  // 腾讯区划
    tengxunStreet?: string,  // 腾讯街道
    tengxunAddress?: string,  // 腾讯地址
    tengxunAddressLng?: string,  // 腾讯地址经度
    tengxunAddressLat?: string,  // 腾讯地址纬度
    baiduProvince?: string,  // 百度省份
    baiduCity?: string,  // 百度城市
    baiduDistrict?: string,  // 百度区划
    baiduStreet?: string,  // 百度街道
    baiduAddress?: string,  // 百度地址
    baiduAddressLng?: string,  // 百度地址经度
    baiduAddressLat?: string,  // 百度地址纬度
}

// 查询参数字段
export type MapResultQueryDTO = QueryDTO & DisableDTO & {
    originAddress?: string,  // 原地址
    originAddressHash?: string,  // 原地址CRC32值
    gaodeProvince?: string,  // 高德省份
    gaodeCity?: string,  // 高德城市
    gaodeDistrict?: string,  // 高德区划
    gaodeStreet?: string,  // 高德街道
    gaodeAddress?: string,  // 高德地址
    gaodeAddressLng?: string,  // 高德地址经度
    gaodeAddressLat?: string,  // 高德地址纬度
    tengxunProvince?: string,  // 腾讯省份
    tengxunCity?: string,  // 腾讯城市
    tengxunDistrict?: string,  // 腾讯区划
    tengxunStreet?: string,  // 腾讯街道
    tengxunAddress?: string,  // 腾讯地址
    tengxunAddressLng?: string,  // 腾讯地址经度
    tengxunAddressLat?: string,  // 腾讯地址纬度
    baiduProvince?: string,  // 百度省份
    baiduCity?: string,  // 百度城市
    baiduDistrict?: string,  // 百度区划
    baiduStreet?: string,  // 百度街道
    baiduAddress?: string,  // 百度地址
    baiduAddressLng?: string,  // 百度地址经度
    baiduAddressLat?: string,  // 百度地址纬度
}

// 基础信息接口
export async function show(id: String): Promise<Response<MapResultBaseVO>> {
    return http.request({
        url: "/api/address/admin/map-result-show?id=" + id,
        method: "GET",
    })
}

// 详细信息接口
export async function detail(id: String): Promise<Response<MapResultDetailVO>> {
    return http.request({
        url: "/api/address/admin/map-result-detail?id=" + id,
        method: "GET",
    })
}

// 分页列表接口
export async function page(dto: MapResultQueryDTO, pageInfo: PageInfo): Promise<Response<Array<MapResultPageVO>>> {
    return http.request({
        url: "/api/address/admin/map-result-page",
        method: "POST",
        data: {
            ...dto,
            pageNum: pageInfo.page,
            pageSize: pageInfo.size,
        },
    })
}

// 全量列表接口
export async function list(dto: MapResultQueryDTO): Promise<Response<Array<MapResultBaseVO>>> {
    return http.request({
        url: "/api/address/admin/map-result-list",
        method: "POST",
        data: dto,
    })
}

// 单个新增接口
export async function create(dto: MapResultCreateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/address/admin/map-result-create",
        method: "POST",
        data: dto,
    })
}

// 单个修改接口
export async function update(dto: MapResultUpdateDTO): Promise<Response<boolean>> {
    return http.request({
        url: "/api/address/admin/map-result-update",
        method: "PUT",
        data: dto,
    })
}

// 删除接口
export async function remove(ids: string | Array<string>): Promise<Response<boolean>> {
    return http.request({
        url: "/api/address/admin/map-result-remove",
        method: "DELETE",
        data: {ids: typeof ids === "string" ? [ids] : ids},
    })
}
