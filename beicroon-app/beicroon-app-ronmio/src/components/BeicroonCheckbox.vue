<script setup lang="ts">
import {computed} from "vue";

const checked = ["checked", "unchecked", "fixed"] as const;

type Checked = typeof checked[number];

interface Props {
  label?: string,
  checked?: Checked,
  disabled?: boolean,
}

const props = withDefaults(defineProps<Props>(), {
  checked: "unchecked",
  disabled: false,
});

const emits = defineEmits(["check", "uncheck"]);

const checkClass = computed(() => {
  if (props.checked === "checked") {
    return "checked";
  }

  if (props.checked === "unchecked") {
    return "unchecked";
  }

  return "fixed";
});

const handleClick = () => {
  if (props.disabled) {
    return;
  }

  if (props.checked === "checked") {
    emits("uncheck");
  } else {
    emits("check");
  }
};
</script>

<template>
  <div class="beicroon-input checkbox" @click="handleClick">
    <i :class="checkClass"></i>
    <label>{{ label }}</label>
  </div>
</template>

<style lang="less">
.beicroon-input.checkbox {
  gap: 6rem;
  width: auto;
  height: 20rem;
  display: flex;
  cursor: pointer;
  align-items: center;

  i {
    width: 13rem;
    height: 13rem;
    position: relative;
    border-radius: 2rem;
    background-color: var(--color-white);
    border: 1rem solid var(--color-primary);

    &.fixed::after {
      top: 1rem;
      left: 1rem;
      content: "";
      width: 9rem;
      height: 9rem;
      position: absolute;
      background-color: var(--color-warning);
    }

    &.checked::after {
      top: 1rem;
      left: 1rem;
      content: "";
      width: 9rem;
      height: 9rem;
      position: absolute;
      background-color: var(--color-primary);
    }

    &.unchecked::after {
      top: 1rem;
      left: 1rem;
      content: "";
      width: 9rem;
      height: 9rem;
      position: absolute;
      background-color: var(--color-white);
    }
  }
}
</style>