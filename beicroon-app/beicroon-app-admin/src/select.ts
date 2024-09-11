import {reactive} from "vue";
import {BaseVO, PageInfo, QueryDTO, Response} from "@/http.ts";

type Page<DTO extends QueryDTO, VO extends BaseVO> = (params: DTO, pageInfo: PageInfo) => Promise<Response<Array<VO>>>;

export type Config<DTO extends QueryDTO, VO extends BaseVO> = {
    label: string,
    placeholder?: string,
    required?: boolean,
    disabled?: boolean,
    options: Array<VO> | Page<DTO, VO>,
    optionValue: string,
    optionLabel: string,
};

export type Select<DTO extends QueryDTO, VO extends BaseVO> = {
    showValue: string,
    options: Array<Array<VO>>,
    hidden: boolean,
    label: string,
    placeholder?: string,
    required?: boolean,
    disabled?: boolean,
    loading: boolean,
    request?: Page<DTO, VO> | null,
    params: DTO,
    optionValue: string,
    optionLabel: string,
    getEvents: () => Record<string, (e: Event) => void>,
    choose: (option: VO) => void,
};

export default function createBeicroonSelect<DTO extends QueryDTO, VO extends BaseVO>(config: Config<DTO, VO>): Select<DTO, VO> {
    const select: Select<DTO, VO> = reactive<Select<DTO, VO>>({
        showValue: null,
        options: [],
        hidden: true,
        label: config.label,
        placeholder: config.placeholder,
        required: config.required,
        disabled: config.disabled,
        loading: false,
        request: null,
        params: {} as DTO,
        optionValue: config.optionValue,
        optionLabel: config.optionLabel,
        getEvents: () => {
            return {
                click: (e: MouseEvent) => {
                    e.stopPropagation();
                    e.stopImmediatePropagation();
                },
                focusin: () => select.hidden = false,
                focusout: () => select.hidden = true,
            };
        },
        choose: (option: VO) => {
            select.hidden = true;
        },
    }) as Select<DTO, VO>;

    if (config.options instanceof Array) {
        select.options.push(config.options);
    } else {
        select.request = config.options;
    }

    return select;
}