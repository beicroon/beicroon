package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueDetailContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted, ref} from "vue";
            import BeicroonInput from "@/components/BeicroonInput.vue";
            import BeicroonButton from "@/components/BeicroonButton.vue";
            import BeicroonLoading from "@/components/BeicroonLoading.vue";
            import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
            import {detail, {{filename}}DetailVO as VO} from "./{{vueFilename}}.http.ts";

            type Props = {
              id: string,
            };

            const props = defineProps<Props>();

            const form = ref<VO | null>(null);

            const emits = defineEmits(["cancel"]);

            async function handleCancel() {
              emits("cancel");
            }

            onMounted(async () => {
              const {data} = await detail(props.id);
            
              form.value = data;
            });
            </script>

            <template>
              <form class="beicroon-dialog-view">
                <div class="beicroon-dialog-input" v-if="form">
                  {{formInput}}
                </div>
                <div class="beicroon-dialog-loading" v-else>
                  <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
                </div>
                <beicroon-line-vertical></beicroon-line-vertical>
                <div class="beicroon-dialog-button">
                  <beicroon-button class="block primary" label="关闭" @click="handleCancel"></beicroon-button>
                </div>
              </form>
            </template>

            <style lang="less">
            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueFilename}}", table.getVueFilename())
                .replace("{{formInput}}", table.getVueFormDisabledInputString());
    }

}
