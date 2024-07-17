<script setup lang="ts">
const props = defineProps({
  loading: {
    type: Boolean,
    default: false,
    required: false,
  }
});

const emits = defineEmits(['click']);

function handleClick() {
  if (props.loading) {
    return;
  }

  emits('click');
}
</script>

<template>
  <button class="button" @click="handleClick">
    <template v-if="props.loading">加载中</template>
    <template v-else><slot></slot></template>
  </button>
</template>

<style lang="less">

</style>