<script setup lang="ts">
import {List} from "@/list.ts";
import {computed, ref, useSlots} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListTable from "@/components/BeicroonListTable.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import BeicroonListPaginator from "@/components/BeicroonListPaginator.vue";

type Props = {
  list: List<any, any>,
};

const props = defineProps<Props>();

const moreSearchHidden = ref(true);

async function toggleMoreSearch() {
  moreSearchHidden.value = !moreSearchHidden.value;

  if (moreSearchHidden.value) {
    document.removeEventListener("click", toggleMoreSearch);
  } else {
    document.addEventListener("click", toggleMoreSearch, {once: true});
  }
}

const slots = useSlots();

const hasMoreSearch = computed(() => !!slots["more-search"]);

const moreSearchSize = computed(() => {
  return Object.values(props.list.params).filter((item) => item).length;
});
</script>

<template>
  <div class="beicroon-list">
    <div class="list-head">
      <div class="head-search">
        <slot name="head-search"></slot>
      </div>
      <div class="head-button">
        <slot name="head-button"></slot>
      </div>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="list-body">
      <beicroon-list-table :list="list">
        <template #title>
          <slot name="table-title"></slot>
        </template>
        <template #body>
          <slot name="table-body"></slot>
        </template>
      </beicroon-list-table>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="list-foot">
      <div class="more-search" @click.stop v-show="hasMoreSearch">
        <beicroon-button class="search-button primary" label="更多筛选" @click="toggleMoreSearch"></beicroon-button>
        <h6 class="search-size">+{{ moreSearchSize }}</h6>
        <form class="search-input" :class="{hidden: moreSearchHidden}">
          <slot name="more-search"></slot>
        </form>
      </div>
      <beicroon-list-paginator class="paginator" :list="list"></beicroon-list-paginator>
    </div>
  </div>
</template>

<style lang="less">
:root {
  --beicroon-height-list-head: 108rem;
  --beicroon-height-list-foot: 60rem;
}

.beicroon-list {
  width: 100%;
  height: 100%;

  .list-head {
    z-index: 2;
    width: 100%;
    display: flex;
    padding: 8rem;
    position: relative;
    flex-direction: column;
    height: var(--beicroon-height-list-head);

    .head-search {
      width: 100%;
      height: 100%;
      display: flex;
      flex-wrap: wrap;
      flex-direction: row;
      align-items: center;
      justify-content: flex-start;
    }

    .head-button {
      gap: 14rem;
      display: flex;
      height: fit-content;
      align-items: center;
      flex-direction: row;
      justify-content: flex-end;
      width: var(--beicroon-width-search-button);
    }
  }

  .list-body {
    z-index: 1;
    width: 100%;
    padding: 8rem;
    position: relative;
    min-height: 300rem;
    height: calc(100% - var(--beicroon-height-list-head) - var(--beicroon-height-list-foot) - 2 * var(--beicroon-height-line));
  }

  .list-foot {
    z-index: 2;
    width: 100%;
    display: flex;
    position: relative;
    align-items: center;
    justify-content: space-between;
    background-color: var(--color-white);
    height: var(--beicroon-height-list-foot);

    .setting {
      gap: 18rem;
      display: flex;
      width: 300rem;
      padding: 0 52rem;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
    }

    .more-search {
      gap: 8rem;
      z-index: 3;
      width: 120rem;
      display: flex;
      position: relative;
      flex-direction: row;
      align-items: center;
      justify-content: center;

      .search-button {
        z-index: 1;
        position: relative;
      }

      .search-size {
        z-index: 1;
        top: -12rem;
        right: 10rem;
        height: 34rem;
        font-size: 18rem;
        position: absolute;
        line-height: 36rem;
        font-style: normal;
        font-weight: normal;
        color: var(--color-warning-deep);
      }

      .search-input {
        gap: 18rem;
        z-index: 1;
        left: 8rem;
        display: grid;
        bottom: 36rem;
        width: 1080rem;
        height: 600rem;
        overflow-y: auto;
        position: absolute;
        border-radius: 6rem;
        padding: 24rem 8rem;
        justify-items: center;
        align-content: flex-start;
        transition: all 180ms linear;
        background-color: var(--color-white);
        grid-template-columns: repeat(3, 1fr);
        border: 1rem solid var(--color-grey-light);
        box-shadow: 0 0 8rem -3rem var(--color-black-30) inset;
      }

      .beicroon-input {
        &.column-3 {
          width: 100%;
          grid-column: span 3;

          .input-area {
            width: 100%;
          }
        }

        &.column-2 {
          width: 100%;
          grid-column: span 2;

          .input-area {
            width: 100%;
          }
        }
      }
    }

    .paginator {
      flex: 1;
    }
  }
}
</style>