<script setup lang="ts">
import {onMounted} from "vue";
import createBeicroonList from "@/list.ts";
import Create from "./SystemMenuCreate.vue";
import Detail from "./SystemMenuDetail.vue";
import Update from "./SystemMenuUpdate.vue";
import BeicroonList from "@/components/BeicroonList.vue";
import BeicroonInput from "@/components/BeicroonInput.vue";
import BeicroonButton from "@/components/BeicroonButton.vue";
import BeicroonListRow from "@/components/BeicroonListRow.vue";
import BeicroonListCell from "@/components/BeicroonListCell.vue";
import BeicroonListCellButton from "@/components/BeicroonListCellButton.vue";
import {page, remove, SystemMenuPageVO as VO, SystemMenuQueryDTO as DTO} from "./system-menu.http.ts";

const list = createBeicroonList<DTO, VO>(page);

onMounted(list.resetSearch);
</script>

<template>
  <beicroon-list class="system-menu-app" :list="list">
    <template #head-search>
      <beicroon-input label="名称" v-model="list.params.name"></beicroon-input>
      <beicroon-input label="路径" v-model="list.params.path"></beicroon-input>
      <beicroon-input type="number" label="排序" v-model="list.params.sorting"></beicroon-input>
    </template>
    <template #head-button>
      <beicroon-button class="block primary" label="重置" @click="list.handleReset"></beicroon-button>
      <beicroon-button class="block primary" label="查询" :loading="list.loading" @click="list.resetSearch"></beicroon-button>
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate(Create)"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="500">名称</beicroon-list-cell>
      <beicroon-list-cell width="500">路径</beicroon-list-cell>
      <beicroon-list-cell width="500">排序</beicroon-list-cell>
      <beicroon-list-cell-button>操作</beicroon-list-cell-button>
    </template>
    <template #table-body>
      <beicroon-list-row v-for="item in list.data">
        <beicroon-list-cell>{{ item.name }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.path }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.sorting }}</beicroon-list-cell>
        <beicroon-list-cell-button>
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item, Detail)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item, Update)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item, remove)"></beicroon-button>
        </beicroon-list-cell-button>
      </beicroon-list-row>
    </template>
    <template #more-search>
      <beicroon-input class="column-three" label="名称" v-model="list.params.name"></beicroon-input>
      <beicroon-input class="column-two" label="路径" v-model="list.params.path"></beicroon-input>
      <beicroon-input label="排序" v-model="list.params.sorting"></beicroon-input>
    </template>
  </beicroon-list>
</template>

<style scoped lang="less">
.system-menu-app {

}
</style>