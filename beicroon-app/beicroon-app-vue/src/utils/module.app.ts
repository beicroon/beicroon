import {reactive} from "vue";
import toast from "@/utils/beicroon.toast";
import http, {BeicroonGenericVO, BeicroonQueryDTO, BeicroonRequestConfig, BeicroonTabVO} from "@/utils/utils.http";

const getPages = async (start: number, end: number) => {
    if (start < 1 || end < 1) {
        return [1];
    }

    const pages = [];

    for (let i = start; i <= end; i++) {
        pages.push(i);
    }

    return pages;
};

const setPages = async (module: BeicroonModulePaginator, page: number, totalSize: number) => {
    module.page = page;
    module.totalSize = totalSize;
    module.totalPage = Math.ceil(totalSize / module.size);

    if (module.totalPage <= 11) {
        module.pages = await getPages(1, module.totalPage);
    } else if (module.page <= 6) {
        module.pages = await getPages(1, 11);
    } else if (module.page + 5 >= module.totalPage) {
        module.pages = await getPages(module.totalPage - 10, module.totalPage);
    } else {
        module.pages = await getPages(module.page - 5, module.page + 5);
    }
};

const handleTabRequest = async <DTO extends BeicroonQueryDTO>(module: BeicroonModuleTab, data: DTO, config: BeicroonRequestConfig) => {
    if (module.loading) {
        return;
    }

    module.loading = true;

    const res = await http<Array<BeicroonTabVO>>(config, data).finally(() => module.loading = false);

    module.data = res.data;
};

const handlePageRequest = async <DTO extends BeicroonQueryDTO, VO extends BeicroonGenericVO>(module: BeicroonModuleApp<DTO, VO>, config: BeicroonRequestConfig, page: number, data: DTO) => {
    if (module.page.loading) {
        return;
    }

    module.page.loading = true;

    const params = {
        ...data,
        tabCode: module.tab.code,
        pageInfo: {page: page, size: module.paginator.size},
    };

    const res = await http<Array<VO>>(config, params).finally(() => module.page.loading = false);

    module.page.data = res.data;

    if (res.pageInfo) {
        await setPages(module.paginator, res.pageInfo.page, res.pageInfo.total);
    }
};

const handleRemoveRequest = async (module: BeicroonModuleRemove, ids: string | Array<string>, config: BeicroonRequestConfig) => {
    if (module.loading) {
        return;
    }

    module.loading = true;

    const data = {ids: ids instanceof Array ? ids : [ids]};

    await http(config, data).finally(() => module.loading = false);
};

export type ModuleRequestConfig = {
    tab: BeicroonRequestConfig,
    page: BeicroonRequestConfig,
    create: BeicroonRequestConfig,
    update: BeicroonRequestConfig,
    detail: BeicroonRequestConfig,
    remove: BeicroonRequestConfig,
};

export type BeicroonModuleTab = {
    code: string,
    data: Array<BeicroonTabVO>,
    loading: boolean,
    setCode: (code: string) => void,
    request: () => Promise<void>,
}

export type BeicroonModulePage<VO extends BeicroonGenericVO> = {
    data: Array<VO>,
    loading: boolean,
    request: (page?: number) => Promise<void>,
}

export type BeicroonModuleRemove = {
    loading: boolean,
    request: (ids: string | Array<string>) => Promise<void>,
}

export type BeicroonModulePaginator = {
    page: number,
    size: number,
    totalSize: number,
    totalPage: number,
    sizes: Array<number>,
    pages: Array<number>,
    setSize: (size: number) => Promise<void>,
    gotoPage: (page: number) => Promise<void>,
    prevPage: () => Promise<void>,
    nextPage: () => Promise<void>,
};

export type BeicroonModuleApp<DTO extends BeicroonQueryDTO, VO extends BeicroonGenericVO> = {
    params: DTO,
    clear: () => void,
    loading: () => boolean,
    page: BeicroonModulePage<VO>,
    remove: BeicroonModuleRemove,
    tab: BeicroonModuleTab,
    paginator: BeicroonModulePaginator,
};

const newModule = <DTO extends BeicroonQueryDTO, VO extends BeicroonGenericVO>(requestConfig: ModuleRequestConfig) => {
    const module = reactive({
        params: {} as DTO,
        clear: () => module.params = {} as DTO,
        loading: () => module.tab.loading || module.page.loading,
        page: {
            data: [] as Array<VO>,
            loading: false,
            request: async (page: number = 1) => {
                await handlePageRequest(module, requestConfig.page, page, module.params);
            },
        } as BeicroonModulePage<VO>,
        remove: {
            loading: false,
            request: async (ids: string | Array<string>) => {
                await handleRemoveRequest(module.remove, ids, requestConfig.remove);

                toast.success("删除成功");
            },
        } as BeicroonModuleRemove,
        tab: {
            code: "all",
            data: [] as Array<BeicroonTabVO>,
            loading: false,
            setCode: async (code: string) => {
                if (code === module.tab.code) {
                    return;
                }

                module.tab.code = code;

                await module.page.request(1);
            },
            request: async () => await handleTabRequest(module.tab, module.params, requestConfig.tab),
        } as BeicroonModuleTab,
        paginator: {
            page: 1,
            size: 30,
            totalSize: 0,
            totalPage: 1,
            sizes: [30, 50, 80, 100],
            pages: [1],
            setSize: async (size: number) => {
                module.paginator.size = size;

                await module.page.request();
            },
            gotoPage: async (page: number) => {
                if (module.paginator.page == page) {
                    return;
                }

                await module.page.request(page)
            },
            prevPage: async () => {
                if (module.paginator.page <= 1) {
                    return;
                }

                await module.page.request(module.paginator.page - 1)
            },
            nextPage: async () => {
                if (module.paginator.page >= module.paginator.totalPage) {
                    return;
                }

                await module.page.request(module.paginator.page + 1)
            },
        } as BeicroonModulePaginator,
    }) as BeicroonModuleApp<DTO, VO>;

    return module;
};

export default newModule;
