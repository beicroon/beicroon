package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueCreateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {reactive, ref} from "vue";
            import BeicroonForm from "@/components/BeicroonForm.vue";
            import BeicroonInput from "@/components/BeicroonInput.vue";
            import BeicroonButton from "@/components/BeicroonButton.vue";
            import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
            import {create, {{filename}}CreateDTO as DTO} from "./{{vueFilename}}.http.ts";

            const loading = ref(false);

            const form = reactive<DTO>({});

            const emits = defineEmits(["cancel", "confirm"]);

            async function handleCancel() {
              emits("cancel");
            }

            async function handleConfirm() {
              loading.value = true;

              await create(form).finally(() => loading.value = false);

              emits("confirm");
            }
            </script>

            <template>
              <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
                <div class="beicroon-dialog-main beicroon-dialog-input">
                  {{formInput}}
                </div>
                <beicroon-line-vertical></beicroon-line-vertical>
                <div class="beicroon-dialog-button">
                  <beicroon-button class="block warning" label="取消" @click="handleCancel"></beicroon-button>
                  <beicroon-button type="submit" class="block primary" label="保存" :loading="loading"></beicroon-button>
                </div>
              </beicroon-form>
            </template>

            <style scoped lang="less">
            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueFilename}}", table.getVueFilename())
                .replace("{{formInput}}", table.getVueFormInputString());
    }

}
