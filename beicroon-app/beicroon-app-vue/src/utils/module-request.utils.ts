import {reactive} from "vue";
import httpUtils, {BeicroonRequestConfig} from "@u/http.utils";

export type BeicroonQueryModule<VO, DTO> = {
    param: DTO,
    data: VO | null,
    loading: boolean,
    request: (params?: Record<string, any>) => Promise<void>,
    setChecked: (checked: Array<any> | undefined, resField: string, childField?: string) => any[],
    getCheckedField: (resField: string, childField?: string) => any[],
};

const newModule = <VO, DTO = Record<string, any>>(requestConfig: BeicroonRequestConfig) => {
    const module = reactive({
        param: {} as DTO,
        data: null,
        loading: false,
        request: async (params?: Record<string, any>) => {
            if (module.loading) {
                return;
            }

            module.loading = true;

            const data = params ? Object.assign({}, module.param, params) : module.param;

            const res = await httpUtils<VO>(requestConfig, data)
                .finally(() => module.loading = false);

            module.data = res.data;
        },
        setChecked: (checked: Array<any>, resField: string, childField?: string) => {
            if (!Array.isArray(checked) || checked.length === 0) {
                return;
            }

            if (!Array.isArray(module.data) || module.data.length === 0) {
                return;
            }

            const set = new Set(checked);

            const mapData = (data: any[]) => {
              data.forEach(item => {
                  if (childField) {
                      const children = item[childField];

                      if (Array.isArray(children) && children.length > 0) {
                          mapData(children);

                          return;
                      }
                  }

                  if (set.has(item[resField])) {
                      item.checked = "checked";
                  }
              });
            };

            mapData(module.data);
        },
        getCheckedField: (resField: string, childField?: string) => {
            const res: any[] = [];

            if (!Array.isArray(module.data) || module.data.length === 0) {
                return res;
            }

            const mapData = (data: any[]) => {
                data.forEach(item => {
                    if (item.checked === "checked" || item.checked === "fixed") {
                        res.push(item[resField]);
                    }

                    if (childField) {
                        const children = item[childField];

                        if (Array.isArray(children) && children.length > 0) {
                            mapData(children);
                        }
                    }
                });
            };

            mapData(module.data);

            return res;
        },
    }) as BeicroonQueryModule<VO, DTO>;

    return module;
}

export default newModule;
