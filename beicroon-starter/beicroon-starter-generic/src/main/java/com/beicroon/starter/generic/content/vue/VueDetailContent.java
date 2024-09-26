package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueDetailContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted, ref} from "vue";
            import ResourceRoleDetailView from "./{{filename}}DetailView.vue";
            import {detail, {{filename}}DetailVO as VO} from "./{{vueFilename}}.http.utils.ts";

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
              <{{vueFilename}}-detail-view :data="form" @cancel="handleCancel"></resource-role-detail-view>
            </template>

            <style scoped lang="less">
            </style>
            """;

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{filename}}", table.getFilename())
                .replace("{{vueFilename}}", table.getVueFilename());
    }

}
