<script setup lang="ts">
import {computed} from "vue";

type Props = {
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  modelValue?: any,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:modelValue"]);

const modelValue = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
})
</script>

<template>
  <div class="beicroon-input beicroon-checkbox" :class="{required: required}">
    <input class="beicroon-input-area"
           type="checkbox"
           :disabled="disabled"
           :placeholder="placeholder"
           v-model="modelValue"
    />
    <span class="beicroon-checkbox-label">{{ label }}</span>
  </div>
</template>

<style lang="less">
.beicroon-checkbox {
  cursor: pointer;
}
</style>