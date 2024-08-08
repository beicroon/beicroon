<script setup lang="ts">
const props = defineProps({
  loading: {
    type: Boolean,
    default: false,
    required: false,
  }
});

const emits = defineEmits(["click"]);

function handleClick() {
  if (props.loading) {
    return;
  }

  emits("click");
}
</script>

<template>
  <button class="el-button" @click="handleClick">
    <template v-if="props.loading">加载中</template>
    <template v-else><slot></slot></template>
  </button>
</template>

<style scoped lang="less">
@import "@/less/colors";

.el-button {
  &.warning {
    color: @warning;
  }

  &.primary {
    color: @primary;
  }

  &.dangerous {
    color: @dangerous;
  }
}
</style>