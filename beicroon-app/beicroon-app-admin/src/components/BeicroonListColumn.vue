<script setup lang="ts">
import {defineProps, inject, onMounted, Ref} from "vue";

type Props = {
  label: string,
  field: string,
};

const props = defineProps<Props>();

const columns: Ref<Array<Props>> | undefined = inject("registerListTableColumn");

onMounted(() => {
  if (!columns) {
    return;
  }

  columns.value.push({label: props.label, field: props.field});
});
</script>

<template>
  <th>
    <div class="beicroon-list-title">{{ label }}</div>
  </th>
</template>

<style lang="less">
</style>