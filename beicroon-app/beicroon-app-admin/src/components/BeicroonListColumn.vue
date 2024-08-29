<script setup lang="ts">
import {defineProps, inject, onMounted, Ref} from "vue";

type Props = {
  label: string,
  field: string,
  width?: string,
};

const props = withDefaults(defineProps<Props>(), {
  width: "auto",
});

const columns: Ref<Array<Props>> | undefined = inject("registerListTableColumn");

function getStyle() {
  return {
    width: props.width == "auto" ? "auto" : props.width + "rem",
  };
}

onMounted(() => {
  if (!columns) {
    return;
  }

  columns.value.push({label: props.label, field: props.field});
});
</script>

<template>
  <th :style="getStyle()">
    <div class="beicroon-list-field">{{ label }}</div>
  </th>
</template>

<style lang="less">
</style>