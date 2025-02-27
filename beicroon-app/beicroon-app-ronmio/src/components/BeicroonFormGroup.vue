<script setup lang="ts">
import {computed} from "vue";

interface Props {
  label: string,
  type?: "block",
}

const props = defineProps<Props>();

const clazz = computed(() => {
  const res = [];

  if (props.type) {
    res.push(props.type);
  }

  return res;
});
</script>

<template>
  <div class="beicroon-form-group">
    <div class="title">{{ label }}</div>
    <div class="content" :class="clazz">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-form-group {
  gap: 24rem;
  width: 100%;
  display: flex;
  padding: 0 24rem;
  flex-direction: column;

  .title {
    font-weight: bold;
    padding: 2rem 12rem;
    border-left: 4rem solid var(--color-primary);
  }

  .content {
    gap: 24rem;
    display: flex;
    flex-wrap: wrap;
    padding: 0 16rem;

    &.block {
      display: block;
      padding: 0 8rem;
    }

    .span2 {
      grid-column: span 2;
    }

    .span3 {
      grid-column: span 3;
    }
  }
}
</style>