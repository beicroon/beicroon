import {reactive} from "vue";
import {BaseVO, PageInfo, QueryDTO, Response} from "@/utils/http.ts";

type Page<DTO extends QueryDTO, VO extends BaseVO> = (params: DTO, pageInfo: PageInfo) => Promise<Response<Array<VO>>>;

export type Config<DTO extends QueryDTO, VO extends BaseVO> = {
    options?: Array<VO>,
    request?: Page<DTO, VO>,
    optionLabel: string,
    optionValue?: string,
    pageSize?: number,
};

export type Select<DTO extends QueryDTO, VO extends BaseVO> = {
    options?: Array<VO>,
    moreOptions: Array<Array<VO>>,
    request?: Page<DTO, VO>,
    params: DTO,
    optionLabel: string,
    optionValue?: string,
    pageInfo: PageInfo,
    hidden: boolean,
    loading: boolean,
    noMore: boolean,
    show: () => Promise<void>,
    hide: () => Promise<void>,
    load: () => Promise<void>,
    search: (keywords?: string) => Promise<void>,
    loadMore: () => Promise<void>,
    handleRequest: () => Promise<void>,
    getLabel: (option: VO) => any,
    getValue: (option: VO) => any,
};

export default function createBeicroonSelect<DTO extends QueryDTO, VO extends BaseVO>(config: Config<DTO, VO>): Select<DTO, VO> {
    const select: Select<DTO, VO> = reactive<Select<DTO, VO>>({
        options: config.options as Array<VO>,
        moreOptions: [] as Array<Array<VO>>,
        request: config.request,
        params: {} as DTO,
        optionLabel: config.optionLabel,
        optionValue: config.optionValue,
        pageInfo: {page: 1, size: config.pageSize || 30} as PageInfo,
        hidden: true,
        loading: false,
        noMore: false,
        show: async () => {
            select.hidden = false;
        },
        hide: async () => {
            select.hidden = true;
        },
        load: async () => {
            if (select.noMore || select.moreOptions.length > 0) {
                return;
            }

            await select.handleRequest();
        },
        search: async (keywords?: string) => {
            select.noMore = false;
            select.moreOptions = [];
            select.pageInfo.page = 1;
            select.params.keywords = keywords;

            await select.load();
        },
        loadMore: async () => {
            if (select.noMore) {
                return;
            }

            select.pageInfo.page++;

            await select.handleRequest();
        },
        handleRequest: async () => {
            if (select.request) {
                select.loading = true;

                const res = await select.request(select.params, select.pageInfo)
                    .finally(() => select.loading = false);

                if (Array.isArray(res.data) && res.data.length > 0) {
                    select.moreOptions.push(res.data);
                }

                select.noMore = !res.data || res.data.length < select.pageInfo.size;
            }
        },
        getLabel: (option: any): any => {
            return option[select.optionLabel];
        },
        getValue: (option: any): any => {
            if (!select.optionValue) {
                return option;
            }

            return option[select.optionValue];
        },
    }) as Select<DTO, VO>;

    return select;
}