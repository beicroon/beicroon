package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueDetailContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onBeforeMount, ref} from "vue";
            import ElInput from "@/components/elements/ElInput.vue";
            import ElButton from "@/components/elements/ElButton.vue";
            import {{{filename}}DetailVO as VO, detail} from "./{{filename}}.http.ts";

            const props = defineProps({
              id: {
                type: String,
                required: true,
              },
            });

            const data = ref<VO>({
              id: props.id,
            });

            onBeforeMount(async () => {
              const res = await detail(props.id);

              if (res.data) {
                data.value = res.data;
              }
            });

            const emits = defineEmits(["hide"]);

            async function cancel() {
              emits("hide");
            }
            </script>

            <template>
              <form class="detail disabled">
                <div class="view">
                  {{vueAppDetailContent}}
                </div>
                <div class="button">
                  <el-button class="cancel" @click="cancel">关闭</el-button>
                </div>
              </form>
            </template>

            <style scoped lang="less">

            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueAppDetailContent}}", table.getVueAppDetailContent().trim());
    }

}
