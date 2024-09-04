<script setup lang="ts">
import {afterSearch, List} from "@/list.ts";
import {computed, onMounted, onUnmounted, ref} from "vue";
import {mouseMove, mouseUp, windowResize} from "@/util.ts";
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

const step = ref(0);

function handleScroll() {
  scrollTop.value = beicroonList.value.scrollTop * (beicroonList.value.clientHeight / beicroonTable.value.clientHeight);

  scrollLeft.value = beicroonList.value.scrollLeft * (beicroonList.value.clientWidth / beicroonTable.value.clientWidth);
}

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

onMounted(() => windowResize(setScroll));
onUnmounted(() => windowResize());

const scrollXActive = ref(false);
const scrollYActive = ref(false);

async function activeX() {
  scrollXActive.value = true;

  document.body.style.userSelect = "none";

  await mouseMove(async (e: MouseEvent) => {
    beicroonList.value.scrollLeft += e.movementX;
  });

  await mouseUp(endScroll);
}

async function activeY() {
  scrollYActive.value = true;

  document.body.style.userSelect = "none";

  await mouseMove(async (e: MouseEvent) => {
    beicroonList.value.scrollTop += e.movementY;
  });

  await mouseUp(endScroll);
}

async function endScroll() {
  scrollXActive.value = false;
  scrollYActive.value = false;

  document.body.style.userSelect = "";

  await mouseMove();
}
</script>

<template>
  <div class="beicroon-list-table" :class="{loading: list.loading}" ref="beicroonList" @scroll="handleScroll">
    <table ref="beicroonTable">
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

  &::-webkit-scrollbar {
    width: 0;
    height: 0;
  }

  table {
    min-width: 100%;
    empty-cells: show;
    width: fit-content;
    text-align: center;
    table-layout: fixed;
    border-spacing: 2rem;
    border-collapse: separate;
  }

  &.loading {
    .beicroon-list-loading {
      display: flex;
      align-items: center;
      justify-content: center;
      height: calc(100% - var(--beicroon-height-list-row));
    }
  }

  .beicroon-list-head {
    top: 2rem;
    z-index: 2;
    position: sticky;
    height: var(--beicroon-height-list-row);

    .beicroon-list-row {
      height: inherit;
      background-color: var(--color-grey-lighter);

      td {
        height: inherit;
      }

      .beicroon-list-cell {
        &:after {
          left: 0;
          top: -2rem;
          z-index: 2;
          content: "";
          width: 100%;
          height: 2rem;
          display: block;
          position: absolute;
          background-color: var(--color-white);
        }
      }

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-body {
    .beicroon-list-row {
      background-color: var(--color-white);
      height: var(--beicroon-height-list-row);

      td {
        height: inherit;
      }

      .beicroon-list-button {
        background-color: inherit;
      }
    }

    .beicroon-list-row:nth-child(even) {
      background-color: var(--color-grey-lightest);

      .beicroon-list-button {
        background-color: inherit;
      }
    }
  }

  .beicroon-list-loading {
    top: 50%;
    left: 50%;
    z-index: 3;
    width: 100%;
    display: none;
    position: absolute;
    transform: translate(-50%, calc(-50% + (var(--beicroon-height-list-row) / 2)));
  }

  .beicroon-list-cell {
    height: 100%;
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
  }

  .beicroon-list-button {
    z-index: 1;
    right: 2rem;
    width: 180rem;
    position: sticky;

    &:before {
      top: 0;
      z-index: 2;
      left: -2rem;
      content: "";
      width: 2rem;
      height: 100%;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }

    &:after {
      top: 0;
      z-index: 2;
      content: "";
      width: 2rem;
      height: 100%;
      right: -2rem;
      display: block;
      position: absolute;
      background-color: var(--color-white);
    }

    .beicroon-list-cell {
      flex-direction: row;
    }
  }
}

.beicroon-list-table-scroll {
  z-index: 3;
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
    background-color: var(--color-grey-light);

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