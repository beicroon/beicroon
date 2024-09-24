<script setup lang="ts">
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  type?: "button" | "submit",
  label: string,
  loading?: boolean,
  disabled?: boolean,
};

const props = withDefaults(defineProps<Props>(), {
  type: "button",
});

const emits = defineEmits(["click"]);

function handleClick(e: MouseEvent) {
  if (props.loading) {
    return;
  }

  emits("click", e);
}
</script>

<template>
<div class="beicroon-button" :class="{disabled: disabled}" @click.stop="handleClick">
  <beicroon-loading v-show="loading" fill="#ffffff" width="20" height="20"></beicroon-loading>
  <button v-show="!loading" :disabled="disabled" :type="type">{{label}}</button>
</div>
</template>

<style lang="less">
.beicroon-button {
  cursor: pointer;
  display: inline-flex;

  &.disabled {
    cursor: not-allowed;
  }

  &.danger {
    color: var(--color-error);
  }

  &.warning {
    color: var(--color-warning);
  }

  &.success {
    color: var(--color-success);
  }

  &.primary {
    color: var(--color-primary);
  }

  &.block {
    display: flex;
    height: 32rem;
    min-width: 80rem;
    border-radius: 8rem;
    align-items: center;
    justify-content: center;

    &.disabled {
      color: var(--color-white);
      background-color: var(--color-grey-light) !important;
    }

    &.error {
      color: var(--color-white);
      background-color: var(--color-error);

      &:active {
        background-color: var(--color-error-deeper);
      }
    }

    &.warning {
      color: var(--color-white);
      background-color: var(--color-warning);

      &:active {
        background-color: var(--color-warning-deeper);
      }
    }

    &.success {
      color: var(--color-white);
      background-color: var(--color-success);

      &:active {
        background-color: var(--color-success-deeper);
      }
    }

    &.primary {
      color: var(--color-white);
      background-color: var(--color-primary);

      &:active {
        background-color: var(--color-primary-deeper);
      }
    }
  }

  button {
    border: none;
    outline: none;
    color: inherit;
    cursor: inherit;
    background: none;
  }
}
</style>