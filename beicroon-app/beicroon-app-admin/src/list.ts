import {BaseVO, PageInfo, QueryDTO, Response} from "@/http.ts";
import {ComponentOptionsMixin, DefineComponent, ExtractPropTypes, PublicProps, Reactive, reactive} from "vue";

type Page<DTO extends QueryDTO, VO extends BaseVO> = (params: DTO, pageInfo: PageInfo) => Promise<Response<Array<VO>>>;

type Remove = (ids: string | Array<string>) => Promise<Response<boolean>>;

type Component = DefineComponent<{}, {}, {}, {}, {}, ComponentOptionsMixin, ComponentOptionsMixin, {}, string, PublicProps, Readonly<ExtractPropTypes<{}>>, {}>;

export type List<DTO extends QueryDTO, VO extends BaseVO> = {
    loading: boolean,
    params: DTO,
    data: Array<VO>,
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
    handleCreate: (component: Component) => Promise<void>,
    handleDetail: (item: VO, component: Component) => Promise<void>,
    handleUpdate: (item: VO, component: Component) => Promise<void>,
    handleRemove: (item: VO, remove: Remove) => Promise<void>,
};

export default function createBeicroonList<DTO extends QueryDTO, VO extends BaseVO>(page: Page<DTO, VO>): Reactive<List<DTO, VO>> {
    const list: Reactive<List<DTO, VO>> = reactive<List<DTO, VO>>({
        loading: false,
        params: {} as DTO,
        data: [] as Array<VO>,
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
            list.params = {} as DTO;
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
        handleCreate: async (component: Component) => {
            console.info(component);
        },
        handleDetail: async (item: VO, component: Component) => {
            console.info(item);
            console.info(component);
        },
        handleUpdate: async (item: VO, component: Component) => {
            console.info(item);
            console.info(component);
        },
        handleRemove: async (item: VO, remove: Remove) => {
            console.info(item);
            console.info(remove);
        },
    });

    return list;
};