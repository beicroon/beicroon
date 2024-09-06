<script setup lang="ts">
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";

type Props = {
  title: string,
};

defineProps<Props>();
</script>

<template>
  <div class="beicroon-dialog-overlay">
    <div class="beicroon-dialog-head">{{ title }}</div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="beicroon-dialog-body">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="less">
:root {
  --beicroon-height-dialog-button: 72rem;
}

.beicroon-dialog-overlay {
  top: 0;
  right: 0;
  height: 100%;
  width: 1080rem;
  position: absolute;
  background-color: var(--color-white);

  .beicroon-dialog-head {
    width: 30%;
    height: 52rem;
    display: flex;
    font-weight: bold;
    align-items: center;
    letter-spacing: 4rem;
    justify-content: center;
    background-color: var(--color-grey-light);
  }

  .beicroon-dialog-body {
    width: 100%;
    height: calc(100% - 53rem);
  }

  .beicroon-dialog-input {
    gap: 8rem;
    display: grid;
    padding: 24rem 0;
    overflow-y: auto;
    justify-items: center;
    align-content: flex-start;
    height: calc(100% - 73rem);
    grid-template-columns: repeat(3, 1fr);

    .input-area {
      width: 300rem;
    }
  }

  .beicroon-dialog-loading {
    display: flex;
    align-items: center;
    justify-content: center;
    height: calc(100% - var(--beicroon-height-dialog-button) - var(--beicroon-height-line));
  }

  .beicroon-dialog-button {
    width: 100%;
    height: var(--beicroon-height-dialog-button);
  }

  .beicroon-dialog-view {
    width: 100%;
    height: 100%;
  }
}
</style>