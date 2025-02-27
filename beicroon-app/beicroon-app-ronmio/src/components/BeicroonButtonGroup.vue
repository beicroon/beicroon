<script setup lang="ts">
import {computed} from 'vue';

interface Props {
  align?: "center" | "around" | "between",
}

const props = withDefaults(defineProps<Props>(), {
  align: 'between',
});

const clazz = computed(() => {
  const res = [];

  res.push(props.align);

  return res;
});
</script>

<template>
  <div class="beicroon-button-group" :class="clazz">
    <slot></slot>
  </div>
</template>

<style lang="less">
.beicroon-button-group {
  width: 138rem;
  display: flex;

  &.center {
    gap: 4rem;
    justify-content: center;
  }

  &.around {
    justify-content: space-around;
  }

  &.between {
    justify-content: space-between;
  }
}
</style>