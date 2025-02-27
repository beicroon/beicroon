<script setup lang="ts">
import {computed} from "vue";
import BeicroonTableCell from "@c/BeicroonTableCell.vue";

interface Props {
  label: string,
  field: string,
  width?: string,
  frozen?: boolean,
  frozenRight?: boolean,
  direction?: "row" | "column",
}

const props = withDefaults(defineProps<Props>(), {
  direction: "column",
});

const clazz = computed(() => {
  const res = [];

  if (props.frozen) {
    res.push("frozen");
  }

  if (props.frozenRight) {
    res.push("frozen-right");
  }

  return res;
});

const getStyle = () => {
  const res = [];

  if (props.width) {
    res.push(`width: ${props.width}`);
  }

  return res;
};
</script>

<template>
  <beicroon-table-cell :column-class="clazz" :style="getStyle()" :label="label"/>
</template>