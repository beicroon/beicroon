<script setup lang="ts">
import {computed, ref} from "vue";

type Props = {
  type?: "text" | "number" | "password",
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  modelValue?: any,
};

const props = withDefaults(defineProps<Props>(), {
  type: "text",
});

const emits = defineEmits(["update:modelValue"]);

const modelValue = computed({
  get: () => props.modelValue,
  set: (val: any) => emits("update:modelValue", val),
})

const active = ref(false);

async function handleFocusin() {
  active.value = true;
}

async function handleFocusout() {
  active.value = false;
}
</script>

<template>
  <div class="beicroon-input" :class="{required: required, active: active}">
    <span class="beicroon-input-label">{{ label }}</span>
    <input class="beicroon-input-area"
           :disabled="disabled"
           :type="type"
           :placeholder="placeholder"
           v-model="modelValue"
           @focusin="handleFocusin"
           @focusout="handleFocusout"
    />
  </div>
</template>

<style lang="less">
.beicroon-input {
  gap: 6rem;
  z-index: 1;
  padding: 6rem;
  user-select: text;
  position: relative;
  border-radius: 6rem;
  align-items: center;
  flex-direction: row;
  display: inline-flex;
  transition: all 300ms linear;
  justify-content: space-between;

  &.required {
    .beicroon-input-label {
      z-index: 1;
      position: relative;

      &:after {
        top: 0;
        left: 0;
        content: "*";
        width: 24rem;
        height: 24rem;
        display: flex;
        font-size: 18rem;
        position: absolute;
        align-items: center;
        justify-content: center;
        color: var(--color-error);
      }
    }
  }

  &.active {
    background-color: var(--color-primary-lighter);

    .beicroon-input-label {
      color: var(--color-white);
      background-color: var(--color-primary-deeper);
    }

    .beicroon-input-area {
      border-color: var(--color-primary);
    }
  }

  &.column {
    gap: 12rem;
    border-radius: 6rem;
    padding: 14rem 18rem;
    flex-direction: column;
    align-items: flex-start;
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
    border: none;
    outline: none;

    &[type="text"], &[type="number"], &[type="password"] {
      width: 300rem;
      height: 32rem;
      padding: 0 8rem;
      line-height: 32rem;
      border-radius: 6rem;
      border: 1rem solid var(--color-grey-deeper);
    }

    &[type="checkbox"] {
      width: 12rem;
      height: 12rem;
      cursor: pointer;
      border: 1rem solid var(--color-grey-deeper);
    }

    &::placeholder {
      color: var(--color-grey);
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