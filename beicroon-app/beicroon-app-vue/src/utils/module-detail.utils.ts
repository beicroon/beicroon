import {reactive} from "vue";
import {ModuleRequestConfig} from "@u/module-app.utils";
import httpUtils, {BeicroonGenericVO} from "@u/http.utils";

export type BeicroonDetailModule<VO> = {
    id: string,
    data: VO | null,
    loading: boolean,
    getData: () => Promise<void>,
};

const newModule = <VO extends BeicroonGenericVO>(requestConfig: ModuleRequestConfig, id: string) => {
    const module = reactive({
        id: id,
        data: null,
        loading: false,
        getData: async () => {
            if (module.loading) {
                return;
            }

            module.loading = true;

            const res = await httpUtils<VO>(requestConfig.detail, {id: module.id})
                .finally(() => module.loading = false);

            module.data = res.data;
        },
    }) as BeicroonDetailModule<VO>;

    return module;
}

export default newModule;
