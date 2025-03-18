<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import BeicroonSelect from "./BeicroonSelect.vue";
import {BeicroonRequestConfig, moduleRequest} from "@/index";

interface Option {
  label: string,
  value: any,
}

interface Props {
  request: BeicroonRequestConfig,
  modelValue?: any,
  size?: "small" | "normal" | "large" | "huge",
  label?: string,
  placeholder?: string,
  disabled?: boolean,
  required?: boolean,
  labelField?: string,
  valueField?: string,
}

const props = withDefaults(defineProps<Props>(), {
  size: "normal",
  labelField: "name",
  valueField: "id",
});

const emits = defineEmits(["update:modelValue"]);

const options = ref<Array<Option>>([]);

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
});

const query = moduleRequest<Array<any>>(props.request);

onMounted(async () => {
  await query.request();

  if (query.data) {
    options.value = query.data.map(item => {
      return {
        label: item[props.labelField],
        value: item[props.valueField],
      }
    });
  }
});
</script>

<template>
  <beicroon-select :label="label" :options="options" v-model="value"/>
</template>

<style lang="less">
</style>