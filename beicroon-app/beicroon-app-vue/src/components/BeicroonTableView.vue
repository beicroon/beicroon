<script setup lang="ts">
import BeicroonLoading from "./BeicroonLoading.vue";
import {computed, onMounted, onUnmounted, ref} from "vue";

interface Props {
  loading?: boolean,
  width?: string,
  height?: "100%" | "max-content" | string,
}

const props = withDefaults(defineProps<Props>(), {
  loading: false,
  width: "100%",
  height: "100%",
});

const style = computed(() => {
  return {
    width: props.width,
    height: props.height,
  };
});

const containerNode = ref<HTMLElement | null>(null);
const scrollNode = ref<HTMLElement | null>(null);
const tableNode = ref<HTMLElement | null>(null);
const scrollbarX = ref<HTMLElement | null>(null);
const scrollbarY = ref<HTMLElement | null>(null);
const thumbX = ref<HTMLElement | null>(null);
const thumbY = ref<HTMLElement | null>(null);

const initScroll = () => {
  if (!scrollNode.value || !tableNode.value || !thumbX.value || !thumbY.value) {
    return;
  }

  const width = ref(0);
  const height = ref(0);

  const {clientWidth, clientHeight} = scrollNode.value;
  const {scrollWidth, scrollHeight} = tableNode.value;

  if (clientWidth >= scrollWidth) {
    width.value = 0;
  } else {
    width.value = Math.max(20, Math.floor(clientWidth * clientWidth / scrollWidth));
  }

  if (clientHeight >= scrollHeight) {
    height.value = 0;
  } else {
    height.value = Math.max(20, Math.floor(clientHeight * clientHeight / scrollHeight));
  }

  thumbX.value.style.width = width.value + "rem";
  thumbY.value.style.height = height.value + "rem";
};

const handleScroll = () => {
  if (!scrollNode.value || !scrollbarX.value || !scrollbarY.value || !thumbX.value || !thumbY.value) {
    return;
  }

  const {scrollLeft, scrollTop} = scrollNode.value;

  thumbY.value.style.top = `${scrollTop * thumbY.value.clientHeight / scrollbarY.value.clientHeight}rem`;
  thumbX.value.style.left = `${scrollLeft * thumbX.value.clientWidth / scrollbarX.value.clientWidth}rem`;
};

const containerObserver = new ResizeObserver(initScroll);
const tableObserver = new ResizeObserver(initScroll);

onMounted(() => {
  initScroll();

  window.addEventListener("resize", initScroll);

  if (containerNode.value) {
    containerObserver.observe(containerNode.value);
  }

  if (tableNode.value) {
    tableObserver.observe(tableNode.value);
  }
});

onUnmounted(() => {
  window.removeEventListener("resize", initScroll);

  containerObserver.disconnect();

  tableObserver.disconnect();
});
</script>

<template>
  <div class="beicroon-table-view" :style="style" ref="containerNode">
    <div class="beicroon-table-main" @scroll="handleScroll" ref="scrollNode">
      <table ref="tableNode">
        <thead>
        <tr>
          <slot name="head"></slot>
        </tr>
        </thead>
        <tbody>
        <slot name="body"></slot>
        </tbody>
      </table>
    </div>
    <div class="beicroon-table-loading" v-if="loading">
      <beicroon-loading fill="#b3e5fc" width="80" height="80"></beicroon-loading>
    </div>
    <div class="beicroon-table-scrollbar x" ref="scrollbarX">
      <div class="beicroon-scrollbar-thumb" ref="thumbX"></div>
    </div>
    <div class="beicroon-table-scrollbar y" ref="scrollbarY">
      <div class="beicroon-scrollbar-thumb" ref="thumbY"></div>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-table-view {
  overflow: hidden;
  position: relative;
  padding: 8rem 8rem 10rem;

  &:before {
    top: 8rem;
    left: 8rem;
    z-index: 7;
    content: "";
    width: 1rem;
    bottom: 10rem;
    display: block;
    position: absolute;
    background-color: var(--color-grey-light);
  }

  &:after {
    top: 8rem;
    z-index: 7;
    right: 8rem;
    content: "";
    width: 1rem;
    bottom: 10rem;
    display: block;
    position: absolute;
    background-color: var(--color-grey-light);
  }

  .beicroon-table-main {
    width: 100%;
    height: 100%;
    overflow: auto;
    position: relative;

    &::-webkit-scrollbar {
      width: 0;
      height: 0;
    }
  }

  table {
    min-width: 100%;
    border-spacing: 0;
    empty-cells: show;
    width: max-content;
    border-collapse: collapse;
  }

  thead, tbody, tr, th, td {
    padding: 0;
    border: none;
  }

  thead {
    top: 0;
    z-index: 6;
    position: sticky;

    .beicroon-table-cell {
      font-weight: bold;
      border-top: 1rem solid var(--color-grey-light);
    }
  }

  tr .beicroon-table-column {
    width: 182rem;
    height: 52rem;

    &.frozen {
      left: 0;
      z-index: 2;
      position: sticky;

      .beicroon-table-cell {
        background-color: var(--color-grey-lighter);

        &:after {
          top: 0;
          content: "";
          width: 1rem;
          right: -1rem;
          height: 100%;
          position: absolute;
          background-color: var(--color-grey-light);
        }
      }
    }

    &.frozen-right {
      right: 0;
      z-index: 2;
      position: sticky;

      .beicroon-table-cell {
        background-color: var(--color-grey-lighter);
      }
    }

    .beicroon-table-cell {
      gap: 4rem;
      width: 100%;
      height: 100%;
      display: flex;
      padding: 8rem;
      align-items: center;
      flex-direction: column;
      justify-content: center;
      background-color: var(--color-white-light);
      border-left: 1rem solid var(--color-grey-light);
      border-bottom: 1rem solid var(--color-grey-light);
    }
  }

  .beicroon-table-loading {
    top: 8rem;
    left: 8rem;
    z-index: 6;
    right: 8rem;
    bottom: 11rem;
    display: flex;
    position: absolute;
    align-items: center;
    justify-content: center;
    background-color: var(--color-black-light-66);
  }

  .beicroon-table-scrollbar {
    z-index: 3;
    position: absolute;

    &.x {
      left: 8rem;
      right: 8rem;
      bottom: 3rem;
      height: 6rem;

      .beicroon-scrollbar-thumb {
        width: 0;
        height: 100%;
      }

      &:before {
        left: 0;
        right: 0;
        top: -2rem;
        content: "";
        height: 1rem;
        display: block;
        position: absolute;
        background-color: var(--color-grey-light);
      }
    }

    &.y {
      top: 8rem;
      width: 6rem;
      right: 1rem;
      bottom: 10rem;

      .beicroon-scrollbar-thumb {
        height: 0;
        width: 100%;
      }
    }
  }

  .beicroon-scrollbar-thumb {
    position: relative;
    border-radius: 4rem;
    background-color: var(--color-grey-light);
  }
}
</style>