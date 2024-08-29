<script setup lang="ts">
type Props = {
  type?: "button" | "submit" | "reset",
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
<div class="beicroon-button" @click.self="handleClick">
  <button :disabled="disabled" :type="type">{{label}}</button>
</div>
</template>

<style lang="less">
.beicroon-button {
  cursor: pointer;
  display: inline-flex;

  &.error {
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

    &.error {
      color: var(--color-white);
      background-color: var(--color-error);
    }

    &.warning {
      color: var(--color-white);
      background-color: var(--color-warning);
    }

    &.success {
      color: var(--color-white);
      background-color: var(--color-success);
    }

    &.primary {
      color: var(--color-white);
      background-color: var(--color-primary);
    }

    &.normal {
      background-color: var(--color-grey-lighter);
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