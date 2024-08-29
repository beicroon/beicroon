<script setup lang="ts">
import {ref, watch} from "vue";
import {PageInfo} from "@/http.ts";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListTable from "@/components/BeicroonListTable.vue";
import BeicroonListColumn from "@/components/BeicroonListColumn.vue";
import BeicroonLineVertical from "@/components/BeicroonLineVertical.vue";
import BeicroonListPaginator from "@/components/BeicroonListPaginator.vue";

const data = ref<Array<any>>([
  {
    name: "名称",
    path: "路径",
    sorting: "排序",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
  {
    name: "名称1",
    path: "路径1",
    sorting: "排序1",
  },
]);

const pageInfo = ref<PageInfo>({
  page: 1,
  size: 15,
  total: 0,
});

watch(pageInfo, () => {

});
</script>

<template>
  <div class="beicroon-list">
    <div class="list-head">
      <div class="head-search">
        <slot name="search"></slot>
      </div>
      <div class="head-button">
        <beicroon-button class="block primary" label="重置"></beicroon-button>
        <beicroon-button class="block primary" label="查询"></beicroon-button>
        <beicroon-button class="block warning" label="新增"></beicroon-button>
      </div>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="list-body">
      <beicroon-list-table :data="data">
        <beicroon-list-column label="名称" field="name" width="500"></beicroon-list-column>
        <beicroon-list-column label="路径" field="path" width="500"></beicroon-list-column>
        <beicroon-list-column label="排序" field="sorting" width="500"></beicroon-list-column>
      </beicroon-list-table>
    </div>
    <beicroon-line-vertical></beicroon-line-vertical>
    <div class="list-foot">
      <div class="setting">
        <beicroon-button class="block normal" label="字段设置"></beicroon-button>
        <beicroon-button class="block normal" label="查询设置"></beicroon-button>
      </div>
      <beicroon-list-paginator v-model="pageInfo"></beicroon-list-paginator>
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
}

.list-head {
  width: 100%;
  display: flex;
  flex-direction: row;

  .head-search {
    flex: 1;
    gap: 24rem;
    display: flex;
    padding: 18rem;
    flex-direction: row;
    align-items: flex-start;
    justify-content: space-between;
  }

  .head-button {
    gap: 13rem;
    width: 130rem;
    display: flex;
    padding: 18rem;
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
    padding: 0 52rem;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }
}
</style>