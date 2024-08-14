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
  <div class="el-button" @click="handleClick">
    <span v-show="props.loading">加载中</span>
    <button type="button" v-show="!props.loading"><slot></slot></button>
  </div>
</template>

<style scoped lang="less">
@import "@/less/colors";

.el-button {
  display: flex;
  cursor: pointer;
  align-items: center;
  border-radius: 8rem;
  justify-content: center;

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