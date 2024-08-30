<script setup lang="ts">
import escToggle from "@/util.ts";
import {PageInfo} from "@/http.ts";
import {computed, defineProps, ref, unref} from "vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListTable from "@/components/BeicroonListTable.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import BeicroonListPaginator from "@/components/BeicroonListPaginator.vue";

type Props = {
  pageInfo: PageInfo,
};

const props = defineProps<Props>();

const emits = defineEmits(["update:pageInfo"]);

const paginator = computed({
  get: () => props.pageInfo,
  set: (val) => emits("update:pageInfo", val),
});

const moreSearch = ref(false);

async function toggleMoreSearch() {
  moreSearch.value = !moreSearch.value;

  if (unref(moreSearch)) {
    await escToggle(async () => {
      moreSearch.value = false;
    });
  }
}
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
      <beicroon-list-table>
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
      <div class="setting">
        <beicroon-button class="block normal" label="字段设置"></beicroon-button>
        <beicroon-button class="block normal" label="查询设置"></beicroon-button>
      </div>
      <div class="search" @click.stop>
        <beicroon-button class="primary" label="更多查询" @click="toggleMoreSearch"></beicroon-button>
        <form class="search-input" v-show="moreSearch">
          <slot name="more-search"></slot>
        </form>
      </div>
      <beicroon-list-paginator class="paginator" v-model="paginator"></beicroon-list-paginator>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-list {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;

  .list-head {
    width: 100%;
    display: flex;
    flex-direction: row;

    .head-search {
      flex: 1;
      gap: 16rem;
      display: flex;
      padding: 18rem;
      flex-wrap: wrap;
      flex-direction: row;
      align-items: flex-start;
      justify-content: space-between;
    }

    .head-button {
      gap: 13rem;
      width: 130rem;
      display: flex;
      padding: 18rem;
      min-height: 100%;
      height: fit-content;
      align-items: center;
      flex-direction: column;
      justify-content: center;
    }
  }

  .list-body {
    flex: 1;
    width: 100%;
    padding: 8rem;
    overflow-y: auto;
  }

  .list-foot {
    width: 100%;
    height: 60rem;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .setting {
      gap: 18rem;
      display: flex;
      width: 300rem;
      padding: 0 52rem;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
    }

    .search {
      z-index: 3;
      width: 120rem;
      display: flex;
      position: relative;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;

      .search-input {
        gap: 18rem;
        z-index: 1;
        bottom: 36rem;
        left: -240rem;
        width: 880rem;
        display: grid;
        padding: 18rem;
        height: 580rem;
        flex-wrap: wrap;
        overflow-y: auto;
        position: absolute;
        border-radius: 6rem;
        justify-items: center;
        background-color: var(--color-white);
        grid-template-columns: repeat(3, 1fr);
        border: 1rem solid var(--color-grey-light);
      }

      .beicroon-input {
        gap: 8rem;
        padding: 8rem;
        flex-direction: column;
        align-items: flex-start;

        &.column-three {
          width: 100%;
          grid-column: span 3;

          .input-area {
            width: 100%;
          }
        }

        &.column-two {
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