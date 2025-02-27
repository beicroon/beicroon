<script setup lang="ts">
import {computed} from "vue";
import BeicroonSelect from "@c/BeicroonSelect.vue";

interface Props {
  label: string,
  modelValue?: any,
  disabled?: boolean,
}

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
});

const options = [
  {label: "保留整数", value: "ZeroDecimalPlace"},
  {label: "保留1位小数", value: "OneDecimalPlace"},
  {label: "保留2位小数", value: "TwoDecimalPlaces"},
];
</script>

<template>
  <beicroon-select :label="label" v-model="value" :options="options" :disabled="disabled"/>
</template>

<style lang="less">

</style>