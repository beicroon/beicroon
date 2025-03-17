package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.database.Table;

public final class VueDetailContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {onMounted} from "vue";
            import newModule from "@u/module-detail.utils.ts";
            import BeicroonButton from "@c/BeicroonButton.vue";
            import DetailView from "@m/{{pathName}}/component/DetailView.vue";
            import config, {DetailVO} from "@m/{{pathName}}/script/module.ts";

            interface Props {
              id: string,
            }

            const props = defineProps<Props>();

            const emits = defineEmits(["hide"]);

            const handleHide = () => emits("hide", false);

            const module = newModule<DetailVO>(config, props.id);

            onMounted(module.getData);
            </script>

            <template>
              <detail-view :data="module.data">
                <template v-slot:button>
                  <b-button size="larger" label="关闭" @click="handleHide"/>
                </template>
              </detail-view>
            </template>

            <style lang="less">

            </style>
            """;

    private VueDetailContent() {

    }

    public static String getContent(Table table) {
        return CONTENT
                .replace("{{className}}", table.getClassName())
                .replace("{{pathName}}", table.getPathName())
                ;
    }

}
