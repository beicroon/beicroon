package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueCreateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {ref} from "vue";
            import toast from "@/utils/toast";
            import {validateForm} from "@/utils/function.ts";
            import FormInput from "@/components/form/FormInput.vue";
            import FormButton from "@/components/form/FormButton.vue";
            import {{{filename}}CreateDTO as DTO, create as submit} from "./{{filename}}.http.ts";

            const form = ref();

            const data = ref<DTO>({});

            const loading = ref(false);

            const emits = defineEmits(["hide", "reload"]);

            function cancel() {
              emits("hide");
            }

            async function confirm() {
              loading.value = true;

              if (!validateForm(form)) {
                await toast("请填写必填项！", true);

                loading.value = false;

                return;
              }

              await submit(data.value).finally(() => loading.value = false);

              await toast("添加成功");

              emits("hide");

              emits("reload");
            }
            </script>

            <template>
              <form class="create" ref="form">
                <div class="view">
                  {{vueAppFormInputContent}}
                </div>
                <div class="button">
                  <form-button class="cancel" @click="cancel" :loading="loading">取消</form-button>
                  <form-button class="confirm" @click="confirm" :loading="loading">保存</form-button>
                </div>
              </form>
            </template>

            <style scoped lang="less">

            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueAppFormInputContent}}", table.getVueAppFormInputContent().trim());
    }

}
