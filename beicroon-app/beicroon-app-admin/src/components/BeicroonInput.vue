<script setup lang="ts">
import {computed} from "vue";

type Props = {
  type?: "text" | "number" | "password",
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
  <span class="input-label">{{label}}</span>
  <input class="input-area" :disabled="disabled" :type="type" :placeholder="placeholder" v-model="value" />
</div>
</template>

<style lang="less">
.beicroon-input {
  gap: 6rem;
  align-items: center;
  flex-direction: row;
  display: inline-flex;
  transition: all 300ms linear;
  justify-content: space-between;

  &.column {
    padding: 8rem;
    border-radius: 6rem;
    flex-direction: column;
    align-items: flex-start;
  }

  .input-label {
    height: 32rem;
    min-width: 100rem;
    line-height: 32rem;
    text-align: center;
    border-radius: 6rem;
    background-color: var(--color-grey-light);
  }

  .input-area {
    width: 240rem;
    outline: none;
    height: 32rem;
    padding: 0 8rem;
    line-height: 32rem;
    border-radius: 6rem;
    border: 1rem solid var(--color-grey-deeper);

    &::placeholder {
      color: var(--color-grey-light);
    }

    &:focus {
      border-color: var(--color-primary);
    }

    &[type="number"] {
      appearance: textfield;
    }

    &[type="number"]::-webkit-outer-spin-button,
    &[type="number"]::-webkit-inner-spin-button {
      appearance: none;
    }
  }
}
</style>