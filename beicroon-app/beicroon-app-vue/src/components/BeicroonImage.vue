<script setup lang="ts">
interface Props {
  src: string,
  alt?: string,
  size?: "small" | "normal" | "large",
}

withDefaults(defineProps<Props>(), {
  size: "normal",
});
</script>

<template>
  <img class="beicroon-image" :class="size" :src="src" :alt="alt" />
</template>

<style lang="less">
.beicroon-image {
  &.small {
    max-width: 80rem;
  }

  &.normal {
    max-width: 180rem;
  }

  &.large {
    max-width: 300rem;
  }
}
</style>