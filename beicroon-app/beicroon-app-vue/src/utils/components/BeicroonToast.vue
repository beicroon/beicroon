<script setup lang="ts">
interface Props {
  message: string,
}

defineProps<Props>();
</script>

<template>
<span class="beicroon-toast-message">{{message}}</span>
</template>

<style lang="less">
.beicroon-toast-message {
  width: 100%;
  display: flex;
  padding: 8rem;
  border-radius: 6rem;
  align-items: center;
  justify-content: center;

  &.error {
    color: var(--color-danger);
    background-color: var(--color-danger-lighter);
    border: 1rem solid var(--color-danger-deeper);
  }

  &.success {
    color: var(--color-success);
    background-color: var(--color-success-lighter);
    border: 1rem solid var(--color-success-deeper);
  }

  &.warning {
    color: var(--color-warning);
    background-color: var(--color-warning-lighter);
    border: 1rem solid var(--color-warning-deeper);
  }

  &.info {
    color: var(--color-primary);
    background-color: var(--color-primary-lighter);
    border: 1rem solid var(--color-primary-deeper);
  }
}
</style>