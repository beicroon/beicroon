import {reactive} from "vue";
import httpUtils from "@u/http.utils.ts";
import toastUtils from "@u/toast.utils.ts";
import {ModuleRequestConfig} from "@u/module-app.utils.ts";

export type BeicroonCreateModule<DTO> = {
    data: DTO,
    loading: boolean,
    submit: (params?: Record<string, any>) => Promise<void>,
};

const newModule = <DTO>(requestConfig: ModuleRequestConfig) => {
    const module = reactive({
        data: {} as DTO,
        loading: false,
        submit: async (params?: Record<string, any>) => {
            if (module.loading) {
                return;
            }

            module.loading = true;

            const data = params ? Object.assign({}, module.data, params) : module.data;

            await httpUtils(requestConfig.create, data).finally(() => module.loading = false);

            toastUtils.success("创建成功");
        },
    }) as BeicroonCreateModule<DTO>;

    return module;
}

export default newModule;
