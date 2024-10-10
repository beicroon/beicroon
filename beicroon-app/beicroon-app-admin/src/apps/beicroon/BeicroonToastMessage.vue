<script setup lang="ts">
type Props = {
  message: string,
};

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

  &.error {
    color: var(--color-error);
    background-color: var(--color-error-lighter);
    border: 1rem solid var(--color-error-deeper);
  }
}
</style>