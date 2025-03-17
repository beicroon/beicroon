<script setup lang="ts">
interface Props {
  label?: string,
  content?: string,
}

defineProps<Props>();
</script>

<template>
  <div class="beicroon-table-field">
    <div class="label">{{label}}：</div>
    <div class="content">{{content}}</div>
  </div>
</template>

<style lang="less">
.beicroon-table-field {
  width: 100%;
  display: flex;
  justify-content: space-between;

  .label {
    width: 88rem;
    text-align: right;
    color: var(--color-black-lighter);
  }

  .content {
    flex: 1;
  }
}
</style>