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
import {page, remove, SystemMenuPageVO as VO, SystemMenuQueryDTO as DTO} from "./system.menu.http.ts";

const list = createBeicroonList<DTO, VO>(page, remove, Create, Detail, Update);

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
      <beicroon-button class="block warning" label="新增" @click="list.handleCreate"></beicroon-button>
    </template>
    <template #table-title>
      <beicroon-list-cell width="500">名称</beicroon-list-cell>
      <beicroon-list-cell width="500">路径</beicroon-list-cell>
      <beicroon-list-cell width="500">排序</beicroon-list-cell>
      <beicroon-list-cell class="beicroon-list-button">操作</beicroon-list-cell>
    </template>
    <template #table-body>
      <beicroon-list-row width="500" v-for="item in list.data">
        <beicroon-list-cell>{{ item.name }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.path }}</beicroon-list-cell>
        <beicroon-list-cell>{{ item.sorting }}</beicroon-list-cell>
        <beicroon-list-cell class="beicroon-list-button">
          <beicroon-button class="primary" label="查看" @click="list.handleDetail(item)"></beicroon-button>
          <beicroon-button class="warning" label="编辑" @click="list.handleUpdate(item)"></beicroon-button>
          <beicroon-button class="danger" label="删除" @click="list.handleRemove(item)"></beicroon-button>
        </beicroon-list-cell>
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