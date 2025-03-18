import {reactive} from "vue";
import http from "./utils.http";
import toast from "./beicroon.toast";
import {ModuleRequestConfig} from "./module.app";

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

            await http(requestConfig.create, data).finally(() => module.loading = false);

            toast.success("创建成功");
        },
    }) as BeicroonCreateModule<DTO>;

    return module;
}

export default newModule;
