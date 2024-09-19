package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueUpdateContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted, reactive} from "vue";
            import BeicroonForm from "@/components/BeicroonForm.vue";
            import BeicroonInput from "@/components/BeicroonInput.vue";
            import BeicroonButton from "@/components/BeicroonButton.vue";
            import BeicroonLoading from "@/components/BeicroonLoading.vue";
            import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
            import {detail, {{filename}}UpdateDTO as DTO, update} from "./{{vueFilename}}.http.ts";

            type Props = {
              id: string,
            };

            const props = defineProps<Props>();

            const loading = reactive({
              set: false,
              get: false,
            });

            const form = reactive<DTO>({
              id: props.id,
            });

            const emits = defineEmits(["cancel", "confirm"]);

            async function handleCancel() {
              emits("cancel");
            }

            async function handleConfirm() {
              loading.set = true;
            
              await update(form).finally(() => loading.set = false);
            
              emits("confirm");
            }

            onMounted(async () => {
              loading.get = true;

              const {data} = await detail(props.id).finally(() => loading.get = false);

              {{formField}}
            });
            </script>

            <template>
              <beicroon-form class="beicroon-dialog-view" @submit="handleConfirm">
                <div class="beicroon-dialog-main beicroon-dialog-input" v-if="!loading.get">
                  {{formInput}}
                </div>
                <div class="beicroon-dialog-loading" v-else>
                  <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
                </div>
                <beicroon-line-vertical></beicroon-line-vertical>
                <div class="beicroon-dialog-button">
                  <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
                  <beicroon-button type="submit" class="block warning" label="保存" :loading="loading.set"></beicroon-button>
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
                .replace("{{formInput}}", table.getVueFormInputString())
                .replace("{{formField}}", table.getVueFormFieldString());
    }

}
