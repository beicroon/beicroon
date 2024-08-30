import {defineComponent, Reactive, reactive} from "vue";
import {PageInfo, Response} from "@/http.ts";

defineComponent()

export type List<DTO, VO> = {
    loading: boolean,
    params: DTO,
    data: Array[VO],
    pageInfo: PageInfo,
    pages: Array<number>,
    choosers: Array<number>,
    choose: (page: number) => Promise<void>,
    setPage: (page: number) => Promise<void>,
    getPages: (start: number, end: number) => Promise<Array<number>>,
    setPages: () => Promise<void>,
    resetSearch: () => Promise<void>,
    handleReset: () => Promise<void>,
    handleSearch: () => Promise<void>,
    handleCreate: () => Promise<void>,
    handleDetail: (item: VO, component: { render: () => void }) => Promise<void>,
    handleUpdate: (item: VO, component: { render: () => void }) => Promise<void>,
    handleRemove: (item: VO, component: { render: () => void }) => Promise<void>,
};

export default function createBeicroonList<DTO, VO>(page: (params: DTO, pageInfo: PageInfo) => Promise<Response<Array[VO]>>): List<DTO, VO> {
    const list: Reactive<List> = reactive({
        loading: false,
        params: {} as DTO,
        data: {} as VO[],
        pageInfo: {page: 1, size: 15, total: 0} as PageInfo,
        pages: [1],
        choosers: [2, 15, 30, 50, 100],
        choose: async (size: number) => {
            if (list.pageInfo.size == size) {
                return;
            }

            list.pageInfo.page = 1;
            list.pageInfo.size = size;

            await list.handleSearch();
        },
        setPage: async (page: number) => {
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
            const total = list.pageInfo.total > 0 ? Math.ceil(list.pageInfo.total / list.pageInfo.size) : 1;

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
            list.params = {};
        },
        handleSearch: async () => {
            if (list.loading) {
                return;
            }

            list.loading = true;

            list.data = [];

            const res = await page(list.params, list.pageInfo).finally(() => {
                list.loading = false;
            });

            list.data = res.data;

            list.pageInfo.total = res.page.total;

            await list.setPages();
        },
        handleCreate: async () => {

        },
        handleDetail: async (ignore: VO, component: { render: () => void }) => {

        },
        handleUpdate: async (ignore: VO, component: { render: () => void }) => {

        },
        handleRemove: async (ignore: VO, component: { render: () => void }) => {

        },
    });

    return list;
};