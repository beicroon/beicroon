<script setup lang="ts">
import createBeicroonSelect from "@/select.ts";
import BeicroonSelect from "@/components/BeicroonSelect.vue";
import {page, ResourceMenuPageVO as VO, ResourceMenuQueryDTO as DTO} from "./resource-menu.http.ts";
import {computed} from "vue";

type Props = {
  label: string,
  modelValue?: string,
};

const props = defineProps<Props>();

const select = createBeicroonSelect<DTO, VO>({
  label: props.label,
  options: page,
  optionValue: "id",
  optionLabel: "name",
});

const value = computed({
  get: () => props.modelValue,
  set: (val?: string) => {
    emits("update:modelValue", val);
  },
});
</script>

<template>
<beicroon-select :select="select" v-model="value"></beicroon-select>
</template>

<style lang="less">

</style>