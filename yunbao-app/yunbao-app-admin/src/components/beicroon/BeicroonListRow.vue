<script setup lang="ts">
type Props = {
  height?: string,
};

const props = defineProps<Props>();

function getStyle() {
  const style: Record<string, string> = {};

  if (props.height) {
    style.height = `${props.height}rem`;
  }

  return style;
}
</script>

<template>
  <tr class="beicroon-list-row" :style="getStyle()"><slot></slot></tr>
</template>

<style lang="less">
</style>