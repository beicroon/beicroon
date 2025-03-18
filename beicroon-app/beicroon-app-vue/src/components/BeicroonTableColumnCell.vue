<script setup lang="ts">
import {computed, VNode} from "vue";
import BeicroonTableCell from "./BeicroonTableCell.vue";

interface Column {
  props: Record<string, any>,
  slots: Record<string, (props: Record<string, any>) => VNode[]>,
}

interface Props {
  column: any,
  item: any,
  index: number,
}

const props = defineProps<Props>();

const clazz = (column: Column) => {
  const res = [];

  if (column.props.hasOwnProperty("frozen")) {
    res.push("frozen");
  }

  if (column.props.hasOwnProperty("frozenRight") || column.props.hasOwnProperty("frozen-right")) {
    res.push("frozen-right");
  }

  return res;
};

const style = (column: Column) => {
  const res = [];

  if (column.props.direction) {
    res.push(`flex-direction: ${column.props.direction}`);
  }

  return res;
};

const label = computed(() => {
  if (!props.column.props.field) {
    return null;
  }

  return props.item[props.column.props.field];
});
</script>

<template>
  <beicroon-table-cell :column-class="clazz(column)" :cell-style="style(column)" :label="label">
    <template v-if="column.slots && column.slots.default">
      <component v-for="node in column.slots.default({...column.props, item, index})" :is="node"></component>
    </template>
  </beicroon-table-cell>
</template>

<style lang="less">

</style>