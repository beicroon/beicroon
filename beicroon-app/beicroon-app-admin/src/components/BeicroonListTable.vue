<script setup lang="ts">
import {afterSearch, List} from "@/utils/list.utils.ts";
import {computed, onMounted, onUnmounted, ref} from "vue";
import BeicroonListRow from "@/components/BeicroonListRow.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";

type Props = {
  list: List<any, any>,
};

const props = defineProps<Props>();

const beicroonList = ref();
const beicroonTable = ref();

const scrollWidth = ref(0);
const scrollHeight = ref(0);

const scrollTop = ref(0);
const scrollLeft = ref(0);

async function setScroll() {
  if (!beicroonList.value || !beicroonTable.value) {
    return;
  }

  scrollWidth.value = (beicroonList.value.clientWidth / beicroonTable.value.clientWidth) * beicroonList.value.clientWidth;

  scrollHeight.value = (beicroonList.value.clientHeight / beicroonTable.value.clientHeight) * beicroonList.value.clientHeight;
}

function handleScroll() {
  scrollTop.value = beicroonList.value.scrollTop * (beicroonList.value.clientHeight / beicroonTable.value.clientHeight);

  scrollLeft.value = beicroonList.value.scrollLeft * (beicroonList.value.clientWidth / beicroonTable.value.clientWidth);

  scrollLeftEnd.value = beicroonList.value?.scrollLeft + beicroonList.value?.clientWidth >= beicroonTable.value?.clientWidth;
}

const scrollLeftEnd = ref(false);

const scrollWidthStyle = computed(() => {
  if (scrollWidth.value >= beicroonList.value?.clientWidth) {
    return null;
  }

  return {
    left: `${scrollLeft.value}rem`,
    width: `${scrollWidth.value}rem`,
  };
});

const scrollHeightStyle = computed(() => {
  if (scrollHeight.value >= beicroonList.value?.clientHeight) {
    return null;
  }

  return {
    top: `${scrollTop.value}rem`,
    height: `${scrollHeight.value}rem`,
  };
});

afterSearch(props.list, setScroll);

onMounted(() => {
  window.addEventListener("resize", setScroll);

  handleScroll();
});

onUnmounted(() => window.removeEventListener("resize", setScroll));

const scrollXActive = ref(false);
const scrollYActive = ref(false);

async function moveX(e: MouseEvent) {
  beicroonList.value.scrollLeft += e.movementX;
}

async function activeX() {
  scrollXActive.value = true;

  document.addEventListener("mousemove", moveX);
  document.addEventListener("mouseup", endScroll);
}

async function moveY(e: MouseEvent) {
  beicroonList.value.scrollTop += e.movementY;
}

async function activeY() {
  scrollYActive.value = true;

  document.addEventListener("mousemove", moveY);
  document.addEventListener("mouseup", endScroll);
}

async function endScroll() {
  scrollXActive.value = false;
  scrollYActive.value = false;

  document.removeEventListener("mousemove", moveX);
  document.removeEventListener("mousemove", moveY);
  document.removeEventListener("mouseup", endScroll);
}
</script>

<template>
  <div class="beicroon-list-table" :class="{loading: list.loading}" ref="beicroonList" @scroll="handleScroll">
    <table :class="{scrolling: !scrollLeftEnd}" ref="beicroonTable">
      <thead class="beicroon-list-head">
      <beicroon-list-row>
        <slot name="title"></slot>
      </beicroon-list-row>
      </thead>
      <tbody class="beicroon-list-body">
      <slot name="body"></slot>
      </tbody>
    </table>
    <div class="beicroon-list-loading">
      <beicroon-loading fill="#b3e5fc" width="100" height="100"></beicroon-loading>
    </div>
    <div class="beicroon-list-table-scroll x">
      <div class="scroll" :class="{active: scrollXActive}" :style="scrollWidthStyle" @mousedown.self="activeX"></div>
    </div>
    <div class="beicroon-list-table-scroll y">
      <div class="scroll" :class="{active: scrollYActive}" :style="scrollHeightStyle" @mousedown.self="activeY"></div>
    </div>
  </div>
</template>

<style lang="less">
:root {
  --beicroon-height-list-row: 72rem;
}

.beicroon-list-table {
  width: 100%;
  height: 100%;
  overflow: auto;
  scrollbar-width: none;
  border: 1rem solid var(--color-grey-light);

  &::-webkit-scrollbar {
    width: 0;
    height: 0;
  }

  table {
    padding: 0;
    border: none;
    min-width: 100%;
    border-spacing: 0;
    empty-cells: show;
    width: fit-content;
    text-align: center;
    table-layout: fixed;
    border-collapse: collapse;

    thead, tbody, tr, td {
      padding: 0;
      border: none;
    }

    &.scrolling {
      .beicroon-list-button {
        &:before {
          top: 0;
          left: 0;
          content: "";
          width: 10rem;
          height: 100%;
          display: block;
          position: absolute;
          box-shadow: -6rem 0 8rem -3rem var(--color-black-30) inset;
        }
      }
    }
  }

  &.loading {
    .beicroon-list-loading {
      opacity: 1;
      visibility: visible;
    }
  }

  .beicroon-list-head {
    top: 0;
    z-index: 1;
    position: sticky;
    height: var(--beicroon-height-list-row);

    .beicroon-list-row {
      height: inherit;

      td {
        height: inherit;
        background-color: var(--color-grey-lighter);
      }

      .beicroon-list-button {
        background-color: var(--color-grey-lighter);
      }
    }
  }

  .beicroon-list-body {
    position: relative;

    .beicroon-list-row {
      height: var(--beicroon-height-list-row);

      td {
        height: inherit;
      }
    }
  }

  .beicroon-list-cell {
    display: flex;
    height: inherit;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    border-right: 1rem solid var(--color-grey-light);
    border-bottom: 1rem solid var(--color-grey-light);
  }

  .beicroon-list-button {
    right: 0;
    z-index: 1;
    width: 180rem;
    position: sticky;
    background-color: var(--color-white);

    .beicroon-list-cell {
      border-right: none;
      flex-direction: row;
    }
  }

  .beicroon-list-loading {
    top: 50%;
    left: 50%;
    opacity: 0;
    z-index: 1;
    display: flex;
    visibility: hidden;
    position: absolute;
    align-items: center;
    justify-content: center;
    width: calc(100% - 18rem);
    transition: all 80ms linear;
    background-color: var(--color-white);
    height: calc(100% - var(--beicroon-height-list-row) - 18rem);
    transform: translate(-50%, calc(-50% + (var(--beicroon-height-list-row) / 2)));
  }
}

.beicroon-list-table-scroll {
  overflow: hidden;
  position: absolute;
  border-radius: 6rem;

  .scroll {
    top: 0;
    left: 0;
    z-index: 1;
    max-width: 100%;
    max-height: 100%;
    position: absolute;
    border-radius: 6rem;
    background-color: var(--color-grey-deeper);

    &.active {
      background-color: var(--color-grey-deep);
    }
  }

  &.x {
    bottom: 0;
    left: 10rem;
    height: 8rem;
    width: calc(100% - 18rem);

    .scroll {
      height: 100%;
    }
  }

  &.y {
    right: 0;
    top: 10rem;
    width: 8rem;
    height: calc(100% - 18rem);

    .scroll {
      width: 100%;
    }
  }
}
</style>