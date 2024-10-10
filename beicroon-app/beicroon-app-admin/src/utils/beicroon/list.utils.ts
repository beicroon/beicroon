import {nextTick, reactive} from "vue";
import toast from "@/utils/beicroon/toast.utils.ts";
import dialog from "@/utils/beicroon/dialog.utils.ts";
import {BaseVO, PageInfo, QueryDTO, Response} from "@/utils/beicroon/http.utils.ts";

type Page<DTO extends QueryDTO, VO extends BaseVO> = (params: DTO, pageInfo: PageInfo) => Promise<Response<Array<VO>>>;

type Remove = (ids: string | Array<string>) => Promise<Response<boolean>>;

export type List<DTO extends QueryDTO, VO extends BaseVO> = {
    name: string,
    loading: boolean,
    params: DTO,
    data: Array<VO>,
    pageInfo: PageInfo,
    pages: Array<number>,
    choosers: Array<number>,
    afterSearchCallbacks: Array<() => Promise<void>>,
    choose: (page: number) => Promise<void>,
    setPage: (page: number) => Promise<void>,
    getPages: (start: number, end: number) => Promise<Array<number>>,
    setPages: () => Promise<void>,
    resetSearch: () => Promise<void>,
    handleReset: () => Promise<void>,
    handleSearch: () => Promise<void>,
    handleCreate: (component: any) => Promise<void>,
    handleDetail: (item: VO, component: any) => Promise<void>,
    handleUpdate: (item: VO, component: any) => Promise<void>,
    handleRemove: (item: VO, remove: Remove) => Promise<void>,
    afterSearch: (callback: () => Promise<void>) => Promise<void>,
    handleSearchCallbacks: () => Promise<void>,
};

export default function createBeicroonList<DTO extends QueryDTO, VO extends BaseVO>(name: string, page: Page<DTO, VO>): List<DTO, VO> {
    const list: List<DTO, VO> = reactive<List<DTO, VO>>({
        name: name,
        loading: false,
        params: {} as DTO,
        data: [] as Array<VO>,
        pageInfo: {page: 1, size: 15} as PageInfo,
        pages: [1],
        choosers: [2, 15, 30, 50, 100],
        afterSearchCallbacks: [] as Array<() => Promise<void>>,
        choose: async (size: number) => {
            if (list.pageInfo.size == size) {
                return;
            }

            list.pageInfo.page = 1;
            list.pageInfo.size = size;

            await list.handleSearch();
        },
        setPage: async (page: number) => {
            if (list.pageInfo.page == page) {
                return;
            }

            list.pageInfo.page = page;

            await list.handleSearch();
        },
        getPages: async (start: number, end: number) => {
            const pages = [];

            for (let i = start; i <= end; i++) {
                pages.push(i);
            }

            return pages;
        },
        setPages: async () => {
            const total = list.pageInfo.total && list.pageInfo.total > 0
                ? Math.ceil(list.pageInfo.total / list.pageInfo.size)
                : 1;

            if (total <= 11) {
                list.pages = await list.getPages(1, total);
            } else if (list.pageInfo.page <= 6) {
                list.pages = await list.getPages(1, 11);
            } else if (list.pageInfo.page + 5 >= total) {
                list.pages = await list.getPages(total - 10, 11);
            } else {
                list.pages = await list.getPages(list.pageInfo.page - 5, list.pageInfo.page + 5);
            }
        },
        resetSearch: async () => {
            list.pageInfo.page = 1;

            await list.handleSearch();
        },
        handleReset: async () => {
            for (const key in list.params) {
                if (Object.prototype.hasOwnProperty.call(list.params, key)) {
                    (list.params as any)[key] = null;
                }
            }
        },
        handleSearch: async () => {
            if (list.loading) {
                return;
            }

            list.loading = true;

            list.data = [];

            const res = await page.call(list, list.params, list.pageInfo).finally(() => {
                list.loading = false;
            });

            Object.assign(list.data, res.data);

            list.pageInfo.total = res.page?.total;

            await list.setPages();

            await nextTick();

            await list.handleSearchCallbacks();
        },
        handleCreate: async (component: any) => {
            await dialog({
                title: `新增[${name}]`,
                message: component,
                confirm: async () => {
                    await toast("保存成功");
                },
                finally: async (flag: boolean) => {
                    flag && await list.resetSearch();
                },
            });
        },
        handleDetail: async (item: VO, component: any) => {
            await dialog({
                title: `查看[${name}]`,
                message: component,
                props: {id: item.id},
            });
        },
        handleUpdate: async (item: VO, component: any) => {
            await dialog({
                title: `编辑[${name}]`,
                message: component,
                props: {id: item.id},
                confirm: async () => {
                    await toast("保存成功");
                },
                finally: async (flag: boolean) => {
                    flag && await list.resetSearch();
                },
            });
        },
        handleRemove: async (item: VO, remove: Remove) => {
            await dialog({
                title:  `[${name}]删除确认`,
                message: "是否删除该数据？删除后数据将无法恢复！",
                confirm: async () => {
                    await remove(item.id);

                    await toast("删除成功");
                },
                finally: async (flag: boolean) => {
                    flag && await list.resetSearch();
                },
            });
        },
        afterSearch: async (callback: () => Promise<void>) => {
            list.afterSearchCallbacks.push(callback);
        },
        handleSearchCallbacks: async () => {
            list.afterSearchCallbacks.forEach(callback => callback());
        },
    }) as List<DTO, VO>;

    return list;
};

export async function afterSearch<DTO extends QueryDTO, VO extends BaseVO>(list: List<DTO, VO>, callback: () => Promise<void>) {
    await list.afterSearch(callback);
}
