<script setup lang="ts">
import {ref, watch} from "vue";

type Props = {
  label: string,
  placeholder?: string,
  required?: boolean,
  disabled?: boolean,
  checked?: "checked" | "indeterminate" | "unchecked",
};

const props = withDefaults(defineProps<Props>(), {
  checked: "unchecked",
});

const emits = defineEmits(["check", "uncheck"]);

const checkStatus = ref(props.checked);

watch(() => props.checked, (value) => {
  checkStatus.value = value;
});

function handleCheck() {
  if (checkStatus.value !== "unchecked") {
    checkStatus.value = "unchecked";

    emits("uncheck");

    return;
  }

  checkStatus.value = "checked";

  emits("check");
}
</script>

<template>
  <div class="beicroon-input beicroon-checkbox" :class="{required: required}" @click="handleCheck">
    <input class="beicroon-input-area"
           type="checkbox"
           :disabled="disabled"
           :placeholder="placeholder"
    />
    <span class="beicroon-checkbox-area" :class="checkStatus"></span>
    <span class="beicroon-checkbox-label">{{ label }}</span>
  </div>
</template>

<style lang="less">
.beicroon-checkbox {
  cursor: pointer;

  .beicroon-input-area {
    opacity: 0;
    position: relative;
  }

  .beicroon-checkbox-area {
    top: 10rem;
    left: 6rem;
    z-index: 1;
    width: 12rem;
    height: 12rem;
    cursor: pointer;
    position: absolute;
    border-radius: 2rem;
    border: 1rem solid var(--color-grey-deeper);

    &.indeterminate {
      background-color: var(--color-warning);
    }

    &.checked {
      background-color: var(--color-primary);
    }
  }
}
</style>