<script setup lang="ts">
import {computed, ref} from "vue";

type Props = {
  title: string,
  width?: string,
};

const props = withDefaults(defineProps<Props>(), {
  width: "1170rem",
});

const moving = ref(false);

const overlayNode = ref<HTMLElement | null>();

const startResize = () => {
  document.body.style.userSelect = "none";

  moving.value = true;

  document.addEventListener("mousemove", resize);
  document.addEventListener("mouseup", endResize);
};

const endResize = () => {
  document.body.style.userSelect = "auto";

  moving.value = false;

  document.removeEventListener("mousemove", resize);
  document.removeEventListener("mouseup", endResize);
};

const resize = (e: MouseEvent) => {
  if (!moving.value) {
    return;
  }

  if (overlayNode.value) {
    overlayNode.value.style.width = `${overlayNode.value.clientWidth - e.movementX}rem`;
  }
};

const style = computed(() => {
  return {
    width: props.width,
  };
});
</script>

<template>
  <div class="beicroon-overlay" :style="style" @click.stop ref="overlayNode">
    <div class="beicroon-overlay-resize" @mousedown="startResize"></div>
    <div class="beicroon-overlay-head">{{ title }}</div>
    <div class="beicroon-overlay-body">
      <slot></slot>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-overlay {
  top: 0;
  right: 0;
  bottom: 0;
  max-width: 96%;
  position: absolute;
  background-color: var(--color-white);

  .beicroon-overlay-resize {
    top: 50%;
    left: 2rem;
    content: "";
    width: 2rem;
    height: 52rem;
    position: absolute;
    cursor: col-resize;
    transform: translateY(-50% - 60rem);
    background-color: var(--color-grey);
  }

  .beicroon-overlay-head {
    height: 52rem;
    display: flex;
    padding: 0 52rem;
    font-weight: bold;
    width: fit-content;
    align-items: center;
    letter-spacing: 4rem;
    justify-content: center;
    background-color: var(--color-grey-lighter);
  }

  .beicroon-overlay-body {
    width: 100%;
    height: calc(100% - 52rem);
    border-top: 1rem solid var(--color-grey-light);
  }
}
</style>