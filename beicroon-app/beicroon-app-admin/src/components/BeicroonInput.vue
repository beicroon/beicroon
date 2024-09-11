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
  set: (val: string | number | undefined) => emits("update:modelValue", val),
})
</script>

<template>
<div class="beicroon-input" :class="{required: required}">
  <span class="beicroon-input-label">{{label}}</span>
  <input class="beicroon-input-area" :disabled="disabled" :type="type" :placeholder="placeholder" v-model="value" />
</div>
</template>

<style lang="less">
.beicroon-input {
  gap: 6rem;
  padding: 6rem;
  border-radius: 6rem;
  align-items: center;
  flex-direction: row;
  display: inline-flex;
  transition: all 300ms linear;
  justify-content: space-between;

  &:focus-within {
    background-color: var(--color-primary-lighter);

    .beicroon-input-label {
      color: var(--color-white);
      background-color: var(--color-primary-deeper);
    }
  }

  &.column {
    gap: 12rem;
    padding: 14rem 18rem;
    border-radius: 6rem;
    flex-direction: column;
    align-items: flex-start;

    .beicroon-input-area {
      width: 300rem;
    }
  }

  .beicroon-input-label {
    height: 32rem;
    min-width: 100rem;
    line-height: 32rem;
    text-align: center;
    border-radius: 6rem;
    background-color: var(--color-grey-light);
  }

  .beicroon-input-area {
    width: 240rem;
    outline: none;
    height: 32rem;
    padding: 0 8rem;
    line-height: 32rem;
    border-radius: 6rem;
    border: 1rem solid var(--color-grey-deeper);

    &::placeholder {
      color: var(--color-grey);
    }

    &:focus {
      border-color: var(--color-primary);
    }

    &[disabled] {
      cursor: text;
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