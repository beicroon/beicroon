<script setup lang="ts">
interface Props {
  label?: any,
  columnClass?: any,
  cellStyle?: any,
  rowspan?: number,
  colspan?: number,
}

defineProps<Props>();
</script>

<template>
  <td class="beicroon-table-column" :class="columnClass" :rowspan="rowspan" :colspan="colspan">
    <div class="beicroon-table-cell" :style="cellStyle">
      <template v-if="label">{{label}}</template>
      <template v-else><slot></slot></template>
    </div>
  </td>
</template>