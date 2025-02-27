<script setup lang="ts">
import {computed, useSlots, VNode} from "vue";
import BeicroonTableView from "@c/BeicroonTableView.vue";
import BeicroonTableColumnCell from "@c/BeicroonTableColumnCell.vue";

interface Props {
  data: Array<any>,
  loading?: boolean,
  width?: string,
  height?: "100%" | "max-content" | string,
}

interface Column {
  props: Record<string, any>,
  slots: Record<string, (props: Record<string, any>) => VNode[]>,
}

defineProps<Props>();

const slots = useSlots();

const columns = computed(() => {
  const nodes: any = slots.default?.({});

  if (nodes?.length === 1) {
    return nodes[0].children.map((node: VNode) => {
      return {
        props: node.props,
        slots: node.children,
      } as Column;
    });
  }

  return nodes.map((node: VNode) => {
    return {
      props: node.props,
      slots: node.children,
    } as Column;
  });
});
</script>

<template>
  <beicroon-table-view :loading="loading" :width="width" :height="height">
    <template v-slot:head>
      <slot></slot>
    </template>
    <template v-slot:body>
      <tr v-for="(item, index) in data">
        <beicroon-table-column-cell v-for="column in columns" :column="column" :item="item" :index="index"/>
      </tr>
    </template>
  </beicroon-table-view>
</template>

<style lang="less">

</style>