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
    noMore: boolean,
    loading: boolean,
    request?: Page<DTO, VO> | null,
    requested: boolean,
    params: DTO,
    pageInfo: PageInfo,
    timer?: any,
    clearTimer: () => void,
    search: (keywords?: string) => Promise<void>,
    handleSearch: () => Promise<void>,
    handleRequest: (clear?: boolean) => Promise<void>,
    loadMoreOptions: () => Promise<void>,
    optionLabel: string,
    optionValue?: string,
    hide: () => void,
    show: () => Promise<void>,
    getLabel: (option: VO) => any,
    getValue: (option: VO) => any,
    reset: () => void,
};

export default function createBeicroonSelect<DTO extends QueryDTO, VO extends BaseVO>(config: Config<DTO, VO>): Select<DTO, VO> {
    const select: Select<DTO, VO> = reactive<Select<DTO, VO>>({
        defaultOptions: [],
        options: [],
        hidden: true,
        noMore: false,
        loading: false,
        request: null,
        requested: false,
        params: {} as DTO,
        pageInfo: {page: 1, size: 30} as PageInfo,
        timer: null,
        clearTimer: () => {
            clearTimeout(select.timer);
        },
        search: async (keywords?: string) => {
            select.clearTimer();

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
        optionLabel: "",
        optionValue: undefined,
        hide: () => select.hidden = true,
        show: async () => {
            select.hidden = false;

            if (select.requested) {
                return;
            }

            await select.handleRequest();
        },
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
        reset: () => {
            select.noMore = false;
            select.loading = false;
            select.requested = false;
            select.params = {} as DTO;
            select.pageInfo = {page: 1, size: select.pageInfo.size} as PageInfo;
            select.options = [];

            if (select.defaultOptions.length > 0) {
                select.options.push(select.defaultOptions);
            }

            select.clearTimer();
        },
    }) as Select<DTO, VO>;

    if (Array.isArray(config.options)) {
        select.defaultOptions = config.options;
    } else {
        select.request = config.options;
    }

    if (select.defaultOptions.length > 0) {
        select.options.push(select.defaultOptions);
    }

    select.optionLabel = config.optionLabel;
    select.optionValue = config.optionValue;

    if (config.pageSize) {
        select.pageInfo.size = config.pageSize;
    }

    return select;
}