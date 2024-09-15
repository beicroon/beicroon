import {reactive} from "vue";
import {BaseVO, PageInfo, QueryDTO, Response} from "@/http.ts";

type Page<DTO extends QueryDTO, VO extends BaseVO> = (params: DTO, pageInfo: PageInfo) => Promise<Response<Array<VO>>>;

export type Config<DTO extends QueryDTO, VO extends BaseVO> = {
    options: Array<VO> | Page<DTO, VO>,
    optionValue: string,
    optionLabel: string,
    pageSize?: number,
    multiple?: boolean,
};

export type Select<DTO extends QueryDTO, VO extends BaseVO> = {
    defaultOptions: Array<VO>,
    options: Array<Array<VO>>,
    hidden: boolean,
    getEvents: () => Record<string, Function>,
    noMore: boolean,
    loading: boolean,
    request?: Page<DTO, VO> | null,
    requested: boolean,
    params: DTO,
    pageInfo: PageInfo,
    timer?: any,
    search: (keywords?: string) => Promise<void>,
    handleSearch: () => Promise<void>,
    handleRequest: (clear?: boolean) => Promise<void>,
    loadMoreOptions: () => Promise<void>,
    optionValue: string,
    optionLabel: string,
    hide: () => void,
    getLabel: (option: VO) => any,
    getValue: (option: VO) => any,
};

export default function createBeicroonSelect<DTO extends QueryDTO, VO extends BaseVO>(config: Config<DTO, VO>): Select<DTO, VO> {
    const select: Select<DTO, VO> = reactive<Select<DTO, VO>>({
        defaultOptions: config.options instanceof Array ? config.options : [],
        options: [],
        hidden: true,
        getEvents: () => {
            return {
                click: (e: MouseEvent) => {
                    e.preventDefault();
                    e.stopPropagation();
                    e.stopImmediatePropagation();
                },
                focusin: async () => {
                    select.hidden = false;

                    if (select.requested) {
                        return;
                    }

                    await select.handleRequest();
                },
                focusout: () => {
                    select.hidden = true;
                },
            };
        },
        noMore: false,
        loading: false,
        request: config.options instanceof Function ? config.options : null,
        requested: false,
        params: {} as DTO,
        pageInfo: {page: 1, size: config.pageSize || 30} as PageInfo,
        timer: null,
        search: async (keywords?: string) => {
            clearTimeout(select.timer);

            select.params.keywords = keywords;

            select.timer = setTimeout(select.handleSearch, 500);
        },
        handleSearch: async () => {
            select.noMore = false;

            await select.handleRequest(true);
        },
        handleRequest: async (clear: boolean = false) => {
            if (select.request && !select.noMore) {
                if (clear) {
                    select.options = [];
                    select.pageInfo.page = 1;

                    if (select.defaultOptions.length > 0) {
                        select.options.push(select.defaultOptions);
                    }
                }

                select.loading = true;

                const res = await select.request.call(select, select.params, select.pageInfo)
                    .finally(() => select.loading = false);

                select.options.push(res.data);

                select.noMore = !res.page || res.data.length < res.page.size;

                select.requested = true;
            }
        },
        loadMoreOptions: async () => {
            select.pageInfo.page++;

            await select.handleRequest();
        },
        optionValue: config.optionValue,
        optionLabel: config.optionLabel,
        hide: () => select.hidden = true,
        getLabel: (option: VO) => {
            if (select.optionLabel) {
                return (option as any)[select.optionLabel];
            }

            return option;
        },
        getValue: (option: VO) => {
            if (select.optionValue) {
                return (option as any)[select.optionValue];
            }

            return option;
        },
    }) as Select<DTO, VO>;

    return select;
}