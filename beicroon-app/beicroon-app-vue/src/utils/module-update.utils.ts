import {reactive} from "vue";
import toastUtils from "@u/toast.utils";
import {ModuleRequestConfig} from "@u/module-app.utils";
import httpUtils, {BeicroonGenericVO} from "@u/http.utils";

export type BeicroonUpdateModule<DTO, VO extends BeicroonGenericVO> = {
    id: string,
    loading: () => boolean,
    getting: boolean,
    setting: boolean,
    data: DTO,
    getData: (callback: (res: VO) => void) => Promise<void>,
    submit: (params?: Record<string, any>) => Promise<void>,
};

const newModule = <DTO, VO extends BeicroonGenericVO>(config: ModuleRequestConfig, id: string) => {
    const module = reactive({
        id: id,
        loading: () => module.getting || module.setting,
        getting: false,
        setting: false,
        data: {id: id} as DTO,
        getData: async (callback: (res: VO) => void) => {
            if (module.loading()) {
                return;
            }

            module.getting = true;

            const res = await httpUtils<VO>(config.detail, {id: module.id})
                .finally(() => module.getting = false);

            callback(res.data);
        },
        submit: async (params?: Record<string, any>) => {
            if (module.loading()) {
                return;
            }

            module.setting = true;

            const data = params ? Object.assign({}, module.data, params) : module.data;

            await httpUtils(config.update, data).finally(() => module.setting = false);

            toastUtils.success("保存成功");
        },
    }) as BeicroonUpdateModule<DTO, VO>;

    return module;
}

export default newModule;
