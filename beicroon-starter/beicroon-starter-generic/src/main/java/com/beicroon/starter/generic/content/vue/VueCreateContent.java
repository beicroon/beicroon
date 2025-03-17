package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueCreateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import BeicroonForm from "@c/BeicroonForm.vue";
            import BeicroonInput from "@c/BeicroonInput.vue";
            import newModule from "@u/module-create.utils.ts";
            import BeicroonButton from "@c/BeicroonButton.vue";
            import BeicroonFormGroup from "@c/BeicroonFormGroup.vue";
            import config, {CreateDTO} from "@m/{{pathName}}/script/module.ts";

            const emits = defineEmits(["hide"]);

            const handleHide = () => emits("hide", false);

            const module = newModule<CreateDTO>(config);

            const handleSubmit = async () => {
              await module.submit();

              emits("hide", true);
            };
            </script>

            <template>
              <b-form @submit="handleSubmit">
                <b-form-group label="基础信息">{{createContent}}
                </b-form-group>
                <template v-slot:button>
                  <b-button size="larger" label="关闭" @click="handleHide"/>
                  <b-button size="larger" label="保存" level="warning" type="submit" :loading="module.loading"/>
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
