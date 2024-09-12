<script setup lang="ts">
import createBeicroonSelect from "@/select.ts";
import {computed, onMounted, ref, watch} from "vue";
import BeicroonSelect from "@/components/BeicroonSelect.vue";
import {page, ResourceMenuPageVO as VO, ResourceMenuQueryDTO as DTO} from "./resource-menu.http.ts";

type Props = {
  label: string,
  showValue?: any,
  modelValue?: any,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:showValue", "update:modelValue"]);

const select = createBeicroonSelect<DTO, VO>({
  label: props.label,
  options: page,
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
<beicroon-select :select="select" v-model:show-value="showValue" v-model:model-value="modelValue"></beicroon-select>
</template>

<style lang="less">

</style>