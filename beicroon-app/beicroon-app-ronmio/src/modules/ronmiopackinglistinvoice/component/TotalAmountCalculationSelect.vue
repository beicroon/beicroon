<script setup lang="ts">
import {computed} from "vue";

interface Props {
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
  {label: "单价*数量", value: "UnitPrice*Count"},
  {label: "单价*体积", value: "UnitPrice*Volume"},
];
</script>

<template>
  <beicroon-select label="总价计算方式" v-model="value" :options="options" :disabled="disabled"/>
</template>

<style lang="less">

</style>