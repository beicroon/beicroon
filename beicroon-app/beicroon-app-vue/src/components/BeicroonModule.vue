<script setup lang="ts">
import {BeicroonModuleApp} from "@/utils";
import {onMounted, onUnmounted, ref, useSlots} from "vue";
import BeicroonTable from "@/components/BeicroonTable.vue";
import BeicroonLoading from "@/components/BeicroonLoading.vue";
import BeicroonPaginator from "@/components/BeicroonPaginator.vue";

interface Props {
  module: BeicroonModuleApp<any, any>,
  tabEnable?: boolean,
  paginatorDisabled?: boolean,
}

withDefaults(defineProps<Props>(), {
  tabEnable: false,
  paginatorDisabled: false,
});

const slots = useSlots();

const moduleNode = ref<HTMLElement | null>(null);
const searchNode = ref<HTMLElement | null>(null);
const actionNode = ref<HTMLElement | null>(null);
const summaryNode = ref<HTMLElement | null>(null);
const tableNode = ref<HTMLElement | null>(null);
const paginatorNode = ref<HTMLElement | null>(null);

const resize = () => {
  if (!moduleNode.value || !tableNode.value) {
    return;
  }

  const height = ref(moduleNode.value.clientHeight);

  if (searchNode.value) {
    height.value -= searchNode.value.clientHeight;
  }

  if (actionNode.value) {
    height.value -= actionNode.value.clientHeight;
  }

  if (summaryNode.value) {
    height.value -= summaryNode.value.clientHeight;
  }

  if (paginatorNode.value) {
    height.value -= paginatorNode.value.clientHeight;
  }

  if (height.value <= 180) {
    tableNode.value.style.height = "180rem";
  } else {
    tableNode.value.style.height = `${height.value}rem`;
  }
};

onMounted(() => {
  resize();

  window.addEventListener("resize", resize);
});

onUnmounted(() => {
  window.removeEventListener("resize", resize);
});
</script>

<template>
  <div class="beicroon-module" ref="moduleNode">
    <div class="beicroon-module-search" v-if="slots.search" ref="searchNode">
      <slot name="search"></slot>
    </div>
    <div class="beicroon-module-action" v-if="slots['action-left'] || slots['action-right']" ref="actionNode">
      <div class="action">
        <slot name="action-left"></slot>
      </div>
      <div class="action">
        <slot name="action-right"></slot>
      </div>
    </div>
    <ul class="beicroon-module-summary" v-if="tabEnable" ref="summaryNode">
      <li v-if=module.tab.loading>
        <beicroon-loading fill="#409eff" width="20" height="20"></beicroon-loading>
      </li>
      <template v-for="item in module.tab.data">
        <li :class="{active: module.tab.code === item.code}" @click="module.tab.setCode(item.code)">
          <span>{{ item.name }}</span>
          <span v-if="item.count > 0">({{ item.count }})</span>
        </li>
      </template>
    </ul>
    <div class="beicroon-module-table" ref="tableNode">
      <beicroon-table :data="module.page.data" :loading="module.loading()">
        <slot name="default"></slot>
      </beicroon-table>
    </div>
    <div class="beicroon-module-paginator" ref="paginatorNode">
      <beicroon-paginator v-if="!paginatorDisabled" :paginator="module.paginator"/>
    </div>
  </div>
</template>

<style lang="less">
.beicroon-module {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .beicroon-module-search {
    gap: 6rem;
    display: grid;
    padding: 6rem 8rem;
    align-content: center;
    justify-items: center;
    grid-template-columns: repeat(auto-fill, minmax(340rem, 1fr));
  }

  .beicroon-module-action {
    height: 26rem;
    display: flex;
    padding: 0 8rem;
    justify-content: space-between;

    .action {
      gap: 8rem;
      display: flex;
    }
  }

  .beicroon-module-summary {
    gap: 6rem;
    width: 100%;
    height: 34rem;
    display: flex;
    padding: 8rem 8rem 0;

    li {
      gap: 2rem;
      display: flex;
      cursor: pointer;
      padding: 0 14rem;
      min-width: 56rem;
      align-items: center;
      border-radius: 4rem;
      background-color: var(--color-grey-lighter);

      &.active {
        color: var(--color-white);
        background-color: var(--color-primary);
      }
    }
  }

  .beicroon-module-table {
    flex: 1;
  }

  .beicroon-module-paginator {
    width: 100%;
    height: 42rem;
    z-index: 8;
    position: relative;
    transform: translateY(-5rem);
  }
}
</style>