package com.beicroon.starter.generic.content.vue;

import com.beicroon.starter.generic.entity.Table;

public class VueSelectContent {

    private static final String CONTENT = """
            <script setup lang="ts">
            import {computed, onMounted, ref, watch} from "vue";
            import createBeicroonSelect from "@/utils/beicroon/select.utils.ts";
            import BeicroonSelect from "@/components/beicroon/BeicroonSelect.vue";
            import {page, {{filename}}PageVO as VO, {{filename}}QueryDTO as DTO} from "./{{vueFilename}}.http.utils.ts";

            type Props = {
              label: string,
              showValue?: any,
              modelValue?: any,
            };

            const props = defineProps<Props>();

            const emits = defineEmits(["update:showValue", "update:modelValue"]);

            const select = createBeicroonSelect<DTO, VO>({
              request: page,
              optionValue: "id",
              optionLabel: "name",
            });

            const showValue = ref(props.showValue);

            onMounted(() => {
              watch(() => props.showValue, (val?: any) => {
                showValue.value = val;
              });

              watch(() => showValue.value, (val?: any) => {
                emits("update:showValue", val);
              });
            });

            const modelValue = computed({
              get: () => props.modelValue,
              set: (val?: any) => {
                emits("update:modelValue", val);
              },
            });
            </script>

            <template>
            <beicroon-select
              :label="label"
              :select="select"
              v-model:show-value="showValue"
              v-model:model-value="modelValue"
              v-bind="$attrs"
            ></beicroon-select>
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
