<script setup lang="ts">
import {computed} from "vue";

type Props = {
  type?: "text" | "password",
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  modelValue?: string | number,
};

const props = withDefaults(defineProps<Props>(), {
  type: "text",
});

const emits = defineEmits(["update:modelValue"]);

const value = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
})
</script>

<template>
<div class="beicroon-input" :class="{required: required}">
  <span>{{label}}</span>
  <input :disabled="disabled" :type="type" :placeholder="placeholder" v-model="value" />
</div>
</template>

<style lang="less">
.beicroon-input {
  gap: 6rem;
  align-items: center;
  flex-direction: row;
  display: inline-flex;
  justify-content: space-between;

  &.column {
    padding: 8rem;
    flex-direction: column;
    align-items: flex-start;
  }

  span {
    height: 32rem;
    min-width: 80rem;
    line-height: 32rem;
    text-align: center;
    border-radius: 6rem;
    background-color: var(--color-grey-lighter);
  }

  input {
    width: 240rem;
    outline: none;
    height: 32rem;
    padding: 0 8rem;
    line-height: 32rem;
    border-radius: 6rem;
    border: 1rem solid var(--color-grey-light);

    &::placeholder {
      color: var(--color-grey-light);
    }

    &:focus {
      border-color: var(--color-primary);
    }
  }
}
</style>