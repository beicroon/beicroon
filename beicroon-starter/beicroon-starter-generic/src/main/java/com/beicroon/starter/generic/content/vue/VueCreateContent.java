package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueCreateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {moduleCreate} from "beicroon-app-vue/utils";
            import config, {CreateDTO} from "@m/{{pathName}}/script/module.ts";
            import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";

            const emits = defineEmits(["hide"]);

            const handleHide = () => {
              if (module.loading) {
                return;
              }

              emits("hide", false);
            };

            const module = moduleCreate<CreateDTO>(config);

            const handleSubmit = async () => {
              await module.submit();

              emits("hide", true);
            };
            </script>

            <template>
              <b-form @submit="handleSubmit">
                <b-form-group label="基础">{{createContent}}
                </b-form-group>
                <template v-slot:button>
                  <b-button size="large" label="关闭" @click="handleHide"/>
                  <b-button size="large" label="保存" level="warning" type="submit" :loading="module.loading"/>
                </template>
              </b-form>
            </template>

            <style lang="less">
            
            </style>
            """;

    private VueCreateContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{pathName}}", table.getPathName())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{createContent}}", table.getVueCreateContent())
                ;
    }

}
