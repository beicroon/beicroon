package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueUpdateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import {moduleUpdate} from "beicroon-app-vue/utils";
            import config, {DetailVO, UpdateDTO} from "@m/{{pathName}}/script/module.ts";
            import {BButton, BForm, BFormGroup, BInput} from "beicroon-app-vue/components";
            
            interface Props {
              id: string,
            }
            
            const props = defineProps<Props>();
            
            const emits = defineEmits(["hide"]);
            
            const handleHide = () => {
              if (module.loading()) {
                return;
              }

              emits("hide", false);
            };
            
            const module = moduleUpdate<UpdateDTO, DetailVO>(config.detail, config.update, props.id);
            
            const handleSubmit = async () => {
              await module.submit();
            
              emits("hide", true);
            };
            
            onMounted(async () => {
              await module.request(data => {{{updateContent}}
              });
            });
            </script>
            
            <template>
              <b-form @submit="handleSubmit" :loading="module.getting">
                <b-form-group label="基础">{{createContent}}
                </b-form-group>
                <template v-slot:button>
                  <b-button size="large" label="关闭" @click="handleHide"/>
                  <b-button size="large" label="保存" level="warning" type="submit" :loading="module.loading()"/>
                </template>
              </b-form>
            </template>
            
            <style lang="less">
            
            </style>
            """;

    private VueUpdateContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{pathName}}", table.getPathName())
                .replace("{{urlName}}", table.getUrlName())
                .replace("{{createContent}}", table.getVueCreateContent())
                .replace("{{updateContent}}", table.getVueUpdateContent())
                ;
    }

}
