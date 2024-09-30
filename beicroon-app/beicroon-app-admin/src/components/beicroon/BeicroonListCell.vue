<script setup lang="ts">
type Props = {
  width?: string,
};

const props = defineProps<Props>();

function getStyle() {
  const style: Record<string, string> = {};

  if (props.width) {
    style.width = `${props.width}rem`;
  }

  return style;
}
</script>

<template>
  <td :style="getStyle()"><div class="beicroon-list-cell"><slot></slot></div></td>
</template>

<style lang="less">
</style>