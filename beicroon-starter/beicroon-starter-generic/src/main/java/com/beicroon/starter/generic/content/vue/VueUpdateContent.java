package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueUpdateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import BeicroonForm from "@c/BeicroonForm.vue";
            import BeicroonInput from "@c/BeicroonInput.vue";
            import newModule from "@u/module-update.utils.ts";
            import BeicroonButton from "@c/BeicroonButton.vue";
            import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
            import config, {DetailVO, UpdateDTO} from "@m/{{pathName}}/script/module.ts";
            
            interface Props {
              id: string,
            }
            
            const props = defineProps<Props>();
            
            const emits = defineEmits(["hide"]);
            
            const handleHide = () => emits("hide", false);
            
            const module = newModule<UpdateDTO, DetailVO>(config, props.id);
            
            const handleSubmit = async () => {
              await module.submit();
            
              emits("hide", true);
            };
            
            onMounted(async () => {
              await module.getData(res => {{{updateContent}}
              });
            });
            </script>
            
            <template>
              <beicroon-form @submit="handleSubmit" :loading="module.getting">
                <beicroon-form-group label="基础信息">{{createContent}}
                </beicroon-form-group>
                <template v-slot:button>
                  <beicroon-button size="larger" label="关闭" @click="handleHide"/>
                  <beicroon-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading()"/>
                </template>
              </beicroon-form>
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
