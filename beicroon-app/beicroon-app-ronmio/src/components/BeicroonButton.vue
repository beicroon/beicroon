<script setup lang="ts">
import {computed} from "vue";
import BeicroonLoading from "@c/BeicroonLoading.vue";

interface Props {
  type?: "button" | "submit" | "reset",
  size?: "small" | "normal" | "larger" | "input",
  level?: "primary" | "danger" | "warning" | "success",
  label: string,
  loading?: boolean,
  disabled?: boolean,
}

const props = withDefaults(defineProps<Props>(), {
  type: "button",
  size: "normal",
  level: "primary",
  disabled: false,
});

const emits = defineEmits(["click"]);

const handleClick = (e: MouseEvent) => {
  if (props.disabled) {
    return;
  }

  emits("click", e);
};

const classes = computed(() => {
  const res = [];

  res.push(props.size);
  res.push(props.level);

  if (props.disabled) {
    res.push("disabled");
  }

  return res;
});
</script>

<template>
<button class="beicroon-button" :class="classes" @click="handleClick" :type="type">
  <template v-if="loading"><beicroon-loading fill="#ffffff" width="20" height="20"/></template>
  <template v-else>{{label}}</template>
</button>
</template>

<style lang="less">
.beicroon-button {
  display: flex;
  border-radius: 4rem;
  align-items: center;
  justify-content: center;
  color: var(--color-white);

  &.small {
    height: 22rem;
    padding: 0 6rem;
    font-size: 12rem;
    min-width: 38rem;
  }

  &.normal {
    height: 26rem;
    padding: 0 8rem;
    min-width: 56rem;
  }

  &.larger {
    height: 30rem;
    padding: 0 12rem;
    min-width: 72rem;
  }

  &.input {
    height: 30rem;
    width: 340rem;
  }

  &.disabled {
    cursor: not-allowed;
  }

  &.danger {
    background-color: var(--color-danger);

    &.disabled {
      background-color: var(--color-danger-lighter);
    }
  }

  &.warning {
    background-color: var(--color-warning);

    &.disabled {
      background-color: var(--color-warning-lighter);
    }
  }

  &.success {
    background-color: var(--color-success);

    &.disabled {
      background-color: var(--color-success-lighter);
    }
  }

  &.primary {
    background-color: var(--color-primary);

    &.disabled {
      background-color: var(--color-primary-lighter);
    }
  }
}
</style>