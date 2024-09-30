package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueDetailViewContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted, ref} from "vue";
            import BeicroonForm from "@/components/beicroon/BeicroonForm.vue";
            import BeicroonInput from "@/components/beicroon/BeicroonInput.vue";
            import BeicroonButton from "@/components/beicroon/BeicroonButton.vue";
            import BeicroonLoading from "@/components/beicroon/BeicroonLoading.vue";
            import BeicroonLineVertical from "@/components/beicroon/BeicroonLineVertical.vue";
            import {{{filename}}DetailVO as VO} from "./{{vueFilename}}.http.utils.ts";

            type Props = {
              data: VO | null,
            };

            defineProps<Props>();

            const emits = defineEmits(["cancel"]);

            async function handleCancel() {
              emits("cancel");
            }
            </script>

            <template>
              <beicroon-form class="beicroon-dialog-view">
                <div class="beicroon-dialog-main beicroon-dialog-input" v-if="form">
                  {{formInput}}
                </div>
                <div class="beicroon-dialog-loading" v-else>
                  <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
                </div>
                <beicroon-line-vertical></beicroon-line-vertical>
                <div class="beicroon-dialog-button">
                  <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
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
                .replace("{{formInput}}", table.getVueFormDisabledInputString());
    }

}
